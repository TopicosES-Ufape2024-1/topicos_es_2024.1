packpage br.edu.ufape.topicos.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Inventory {
    @Id   
    @GeneratedValue(strategy = GeneratedType.IDENTITY)
    private long id:
    private Double amount;
}