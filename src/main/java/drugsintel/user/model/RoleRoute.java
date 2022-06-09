package drugsintel.user.model;


import lombok.*;

import javax.persistence.*;

import drugsintel.user.model.id.RoleRouteId;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@Table(schema= "sign",name = "role_route")
public class RoleRoute {

    @EmbeddedId
    RoleRouteId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role roleId;

    @ManyToOne
    @MapsId("routeId")
    @JoinColumn(name = "route_id")
    private Route routeId;

}
