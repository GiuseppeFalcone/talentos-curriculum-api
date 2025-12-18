package com.certimetergroup.talentos.curriculumapi.model;

import com.certimetergroup.talentos.commons.enumeration.DrivingLicense;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curriculum")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID cannot be null.")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 255)
    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Size(max = 255)
    @Column(name = "home_address")
    private String homeAddress;

    @Size(max = 255)
    @Column(name = "work_address")
    private String workAddress;

    @Column(name = "marital_status")
    private Boolean maritalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "driving_license")
    private DrivingLicense drivingLicense;

    @Column(name = "has_car")
    private Boolean hasCar;

    @Column(name = "open_for_travel")
    private Boolean openForTravel;

    @Lob
    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Builder.Default
    @OneToMany(
            mappedBy = "curriculum",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Education> educationHistory = new HashSet<>();

    @Builder.Default
    @OneToMany(
            mappedBy = "curriculum",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Project> projects = new HashSet<>();

    @Builder.Default
    @OneToMany(
            mappedBy = "curriculum",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<ProjectDomainOption> domainOptions = new HashSet<>();

    public void addEducation(Education education) {
        educationHistory.add(education);
        education.setCurriculum(this);
    }

    public void removeEducation(Education education) {
        educationHistory.remove(education);
        education.setCurriculum(null);
    }

    public void addProject(Project project) {
        projects.add(project);
        project.setCurriculum(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.setCurriculum(null);
    }

    public void addDomainOption(ProjectDomainOption option) {
        domainOptions.add(option);
        option.setCurriculum(this);
    }

    public void removeDomainOption(ProjectDomainOption option) {
        domainOptions.remove(option);
        option.setCurriculum(null);
    }
}