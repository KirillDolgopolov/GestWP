package es.winepalace.gestwp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Table
public class Shop {
    @Id
    @Column("shopID")
    private Integer ID;
    @NotNull
    private String shopName;
    @NotNull
    private String shopAddress;
    @NotNull
    private String shopPhone;
    @NotNull
    private String shopEmail;
}