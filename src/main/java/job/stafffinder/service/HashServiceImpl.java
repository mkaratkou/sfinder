package job.stafffinder.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService {
    @Override
    public String hashFor(String string) {
        return DigestUtils.md5Hex(string);
    }
}
