package cz.muni.fi.hauntedhouse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * @author Ondrej Krcma 451363
 */
public class Bogeyman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String name;

    @Enumerated
    @NotNull
    private BogeymanType type;

    @Column
    private Ability ability;

    @Column
    private House house;

    @Column
    //TODO @Temporal(TemporalType.TIME)
    private LocalTime hauntStartTime;

    @Column
    //TODO @Temporal(TemporalType.TIME)
    private LocalTime hountEndTime;

    @Column(length = 2000)
    private String description;

    @Column(length = 500)
    private String reason;

    public Bogeyman(){}

    // TODO: constructor?

    // TODO: add compare and hash
}
