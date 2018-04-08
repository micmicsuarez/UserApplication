package ph.com.masagana.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ph.com.masagana.type.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String middleName;

    @NonNull
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    @NonNull
    private Date dateCreated = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    @Nullable
    private Date dateVerified;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    @Nullable
    private Date dateDeactivated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    @Nullable
    private Date lastLogin;

    @Email
    @NonNull
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status = Status.UNVERIFIED;

    @Nullable
    private String addressOne;

    @Nullable
    private String addressTwo;

    @Nullable
    private String addressThree;
}
