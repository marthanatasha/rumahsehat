package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.TagihanModel;
import apap.tugas.akhir.rumahsehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanService {
    @Autowired
    private TagihanDb tagihanDb;

    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }
}