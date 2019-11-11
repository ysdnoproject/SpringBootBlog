package com.example.demo.doma.entity;

import com.example.demo.doma.annotation.TimestampableEntity;
import com.example.demo.doma.listener.CommonListener;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.seasar.doma.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

import static com.example.demo.constants.MySqlPlatform.LENGTH_LIMIT_TEXT;

@Entity(listener = CommonListener.class)
@Data
@Table(name = "blog")
@TimestampableEntity
public class Blog implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @NotEmpty
    @Length(max = 255)
    private String title;

    @NotEmpty
    @Length(max = LENGTH_LIMIT_TEXT)
    private String content;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "author_id")
    private Long author_id;
}
