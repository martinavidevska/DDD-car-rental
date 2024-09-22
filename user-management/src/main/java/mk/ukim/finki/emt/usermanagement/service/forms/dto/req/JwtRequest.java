package mk.ukim.finki.emt.usermanagement.service.forms.dto.req;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String username;
    private String password;



}
