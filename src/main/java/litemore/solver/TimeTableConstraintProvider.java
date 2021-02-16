package litemore.solver;

import java.time.Duration;
import litemore.domain.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                ///roomConflict(constraintFactory),
                teacherConflict(constraintFactory),
                studentGroupConflict(constraintFactory),
                // Soft constraints
                //teacherRoomStability(constraintFactory),
                teacherTimeEfficiency(constraintFactory),
                studentGroupSubjectVariety(constraintFactory)
        };
    }

//    Constraint roomConflict(ConstraintFactory constraintFactory) {
//        // A room can accommodate at most one lesson at the same time.
//        return constraintFactory
//                // Select each pair of 2 different lessons ...
//                .fromUniquePair(Lesson.class,
//                        // ... in the same timeslot ...
//                        Joiners.equal(Lesson::getTimeslot),
//                        // ... in the same room ...
//                        Joiners.equal(Lesson::getRoom))
//                // ... and penalize each pair with a hard weight.
//                .penalize("Room conflict", HardSoftScore.ONE_HARD);
//    }

    Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot))
                .filter((lesson, lesson2) -> {
                    boolean isContained = false;
                    for(String id: lesson.getTeacherId().split(", ")){
                        System.out.println("lesson1 id = "+lesson.getId());
                        System.out.println("lesson2 id = "+lesson2.getId());
                        if (lesson2.getTeacherId().contains(id)){
                            isContained = true;
                            break;
                        }
                    }
                    return isContained;
                })
                //  .filter((lesson1, lesson2) -> Arrays.stream(lesson1.getTeacherId().split(",")).anyMatch( id -> lesson2.getTeacherId().contains(id) ))
                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }



    Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot)
                ).filter(
                        (lesson, lesson2) -> {
                            boolean isContained = false;
                            for(String className: lesson.getClassName()){
                                if (lesson2.getClassName().contains(className)){
                                    isContained = true;
                                    break;
                                }
                            }
                            return isContained;
                        }
                )
                .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }

//    Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
//        // A teacher prefers to teach in a single room.
//        return constraintFactory
//                .fromUniquePair(Lesson.class,
//                        Joiners.equal(Lesson::getTeacher))
//                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
//                .penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
//    }

    Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTeacherId),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);
    }

    Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        // A student group dislikes sequential lessons on the same subject.
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getSubjectId),
                        Joiners.equal(Lesson::getClassName),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .penalize("Student group subject variety", HardSoftScore.ONE_SOFT);
    }

}
