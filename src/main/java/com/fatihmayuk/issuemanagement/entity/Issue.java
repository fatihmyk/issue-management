package com.fatihmayuk.issuemanagement.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
/**
 *
 * Fatih Mayuk
 *
 */
@Entity
@Table(name = "issue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Issue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description",length = 1000)
    private String description;

    @Column(name = "details",length = 4000)
    private String details;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @JoinColumn(name = "assignee_user_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private User assignee;

    @JoinColumn(name ="project_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    private Project project;


}
