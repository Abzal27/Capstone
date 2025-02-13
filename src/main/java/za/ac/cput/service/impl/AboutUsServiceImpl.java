package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.AboutUs;
import za.ac.cput.repository.AboutUsRepository;
import za.ac.cput.service.IAboutUsService;

import java.util.List;

@Service
public class AboutUsServiceImpl implements IAboutUsService {

    private AboutUsRepository repository;

    @Autowired
    private AboutUsServiceImpl(AboutUsRepository repository){
        this.repository = repository;
    }
    @Override
    public AboutUs create(AboutUs aboutUs) {
        return this.repository.save(aboutUs);
    }

    @Override
    public AboutUs read(int id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public AboutUs update(AboutUs aboutUs) {
        if (this.repository.existsById(aboutUs.getId())){
            return this.repository.save(aboutUs);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AboutUs> getAll() {
        List<AboutUs> all = this.repository.findAll();
        return all;
    }
}
