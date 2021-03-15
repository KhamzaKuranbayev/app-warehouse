package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Result save(Currency currency) {
        if(currencyRepository.existsByName(currency.getName()))
            return new Result("Bunday nomli valyuta mavjud!", false);

        currencyRepository.save(currency);
        return new Result("Muvaffaqiyatli saqlandi!", true);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency findById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElse(null);
    }

    @Override
    public Result edit(Currency currency, Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(optionalCurrency.isPresent()){
            optionalCurrency.get().setName(currency.getName());
            currencyRepository.save(optionalCurrency.get());
            return new Result("Valyuta ma'lumotlari yangilandi!", true);
        }
        return new Result("Bunday id li valyuta mavjud emas!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(optionalCurrency.isPresent()){
            currencyRepository.deleteById(id);
            return new Result("Valyuta o'chirildi!", true);
        }
        return new Result("Bunday id li valyuta mavjud emas!", false);
    }
}
