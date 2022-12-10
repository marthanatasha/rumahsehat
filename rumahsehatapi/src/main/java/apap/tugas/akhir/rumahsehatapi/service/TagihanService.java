package apap.tugas.akhir.rumahsehatapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehatapi.models.TagihanModel;
import apap.tugas.akhir.rumahsehatapi.repository.TagihanDb;

@Service
@Transactional
public class TagihanService {
    @Autowired
    private TagihanDb tagihanDb;

    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }

    public TagihanModel getTagihanById(String id) {
        return tagihanDb.findById(id).get();
    }

    public void addTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
    }

    public TagihanModel updateTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
        return tagihan;
    }

    public TagihanModel deleteTagihan(TagihanModel tagihan) {
        tagihanDb.delete(tagihan);
        return tagihan;
    }
}
