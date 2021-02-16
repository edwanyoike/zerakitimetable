/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package litemore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lesson")
public class Lesson implements Serializable {

    @PlanningId
    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private String subjectId;

    @Transient
    private String teacherId;

    @Transient
    private String streamId;

    @Transient
    private String className;



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.EAGER)
    @JoinTable(name = "lesson_stream",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "streams_id")
    )
    private Set<ClassStream> stream = new HashSet<>();



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.EAGER)
    @JoinTable(name = "lesson_subject",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<ClassSubject> classSubjects = new HashSet<>();


//    @ManyToOne
//    @JoinColumn(name = "teacher_id", nullable = false)
//    private Teacher teacher;



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.EAGER)
    @JoinTable(name = "lesson_teacher",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers = new HashSet<>();


    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    @ManyToOne
    private Timeslot timeslot;


    public String getSubjectId() {
        StringBuilder subjectId = new StringBuilder();
        if (getClassSubjects().size() > 0) {
            for (ClassSubject subject : getClassSubjects()) {
                subjectId.append(subject.getId());
                subjectId.append(",");
            }
            return subjectId.toString();
        }
        return this.subjectId;
    }

    public String getTeacherId() {

        StringBuilder teacherId = new StringBuilder();
        if (getTeachers().size() > 0) {

            for (Teacher teacher : getTeachers()) {
                teacherId.append(teacher.getId());
                teacherId.append(",");
            }
            return teacherId.toString();
        } else return this.teacherId;
    }

    public String getStreamId() {

        StringBuilder streamIds = new StringBuilder();
        if (getStream().size() > 0) {
            for (ClassStream stream : getStream()) {
                streamIds.append(stream.getId());
                streamIds.append(",");
            }
            return teacherId;
        } else return this.streamId;
    }

    public List<String> getClassName() {

        List<String> classNames = new ArrayList<>();

        if (getStream().size() > 0) {
            for (ClassStream stream : getStream()) {
                classNames.add(stream.getClassName());
            }

        }
        return classNames;
    }


//    public Lesson(ClassSubject subject, Teacher teacher) {
//        this.classSubject = subject;
//        this.teacher = teacher;
//    }
//
//    public Lesson(long id, ClassSubject subject, Teacher teacher, Timeslot timeslot) {
//        this(subject, teacher);
//        this.id = id;
//        this.timeslot = timeslot;
//    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                ", streamId=" + streamId +
                ", className='" + className + '\'' +
                ", stream=" + stream +
                ", classSubject=" + classSubjects +
                ", teacher=" + teachers +
                ", timeslot=" + timeslot +
                '}';
    }


    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }

    public Set<ClassSubject> getClassSubjects() {
        return classSubjects;
    }

    public void setClassSubjects(Set<ClassSubject> classSubjects) {
        this.classSubjects = classSubjects;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }


}
