package es.winepalace.gestwp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Shop information")
public class ShopDTO {
    @Schema(description = "Name of shop")
   // @NotBlank
    private String shopName;
    @Schema(description = "Address of shop")
    //@NotBlank
    private String shopAddress;
    @Schema(description = "Phone of shop")
    //@NotBlank
    private String shopPhone;
    //@NotBlank
    @Schema(description = "Email of shop")
    private String shopEmail;
}
