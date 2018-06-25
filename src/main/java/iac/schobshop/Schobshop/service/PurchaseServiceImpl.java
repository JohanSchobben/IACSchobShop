package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.exceptions.PurchaseException;
import iac.schobshop.Schobshop.model.Purchase;
import iac.schobshop.Schobshop.model.PurchaseLine;
import iac.schobshop.Schobshop.model.PurchaseState;
import iac.schobshop.Schobshop.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase makePurchase(Purchase purchase) {

        for(PurchaseLine purchaseLine : purchase.getPurchaseLines()){
            if (!purchaseLine.getProduct().isSellable()){
                throw new PurchaseException("can not sell unsellable items");
            }
        }
        purchase.setPurchaseState(PurchaseState.IN_PROGRESS);
        return purchaseRepository.save(purchase);
    }
}
