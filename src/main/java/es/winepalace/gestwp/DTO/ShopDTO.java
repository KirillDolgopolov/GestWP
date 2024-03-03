package es.winepalace.gestwp.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDTO {
    private Integer shopID;
    private String shopName;
    private String shopAddress;
    private String shopPhone;
    private String shopEmail;
}
