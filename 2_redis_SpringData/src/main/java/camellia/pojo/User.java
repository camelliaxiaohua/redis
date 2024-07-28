package camellia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Datetime: 2024/7/27下午3:52
 * @author: Camellia.xioahua
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private Integer age;
}
