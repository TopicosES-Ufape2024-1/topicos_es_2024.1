package br.edu.ufape.topicos.inventory.amqp;


import br.edu.ufape.topicos.inventory.message.CreateProductInventoryDTO;
import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.service.InventoryService;
import br.edu.ufape.topicos.inventory.service.WarehouseService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedListener {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private WarehouseService warehouseService;

    @RabbitListener(queues = "create.product.inventory")
    public void receiveProductCreated(CreateProductInventoryDTO message) {
        String mensagem = """
                --------------------------------
                Recebendo mensagem de criação de produto
                Produto: %d
                Quantidade: %d
                Warehouse: %d
                --------------------------------
                """.formatted(message.productId(), message.quantity(), message.warehouseId());
        System.out.println(mensagem);

        var warehouse = warehouseService.findWarehouseById(message.warehouseId()).orElseThrow();

        Inventory ivt = new Inventory();
        ivt.setProductId(message.productId());
        ivt.setQuantity(message.quantity());
        ivt.setWarehouse(warehouse);

        inventoryService.saveInventory(ivt);
        System.out.println("Estoque criado do produto " + message.productId() + " no armazém " + message.warehouseId() + " com quantidade " + message.quantity());
    }
}
