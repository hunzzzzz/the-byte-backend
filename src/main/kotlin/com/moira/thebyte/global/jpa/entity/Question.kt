package com.moira.thebyte.global.jpa.entity

import jakarta.persistence.*

@Entity
@Table(name = "QUESTION", schema = "THE_BYTE")
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "question_by", nullable = false)
    var questionBy: User,

    @ManyToOne
    @JoinColumn(name = "question_to", nullable = true)
    var questionTo: User? = null,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    var content: String,

    @Column(name = "view_count", nullable = false)
    var viewCount: Int = 0,

    @Column(name = "ai_answer", nullable = false)
    var aiAnswer: Boolean = false
) : BaseEntity()