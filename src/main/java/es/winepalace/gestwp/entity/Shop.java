package es.winepalace.gestwp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer shopID;

    @NotNull
    private String shopName;
    @NotNull
    private String shopAddress;
    @NotNull
    private String shopPhone;
    @NotNull
    private String shopEmail;
}