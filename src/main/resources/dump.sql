@Table(name="users")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name="users_Id"))
    @Column(name = "roles")
    private Set<Roles> roles;
    @CreationTimestamp
    LocalDateTime createdDate;
    @UpdateTimestamp
    LocalDateTime updatedDate;
}
CREATE