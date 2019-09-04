package job.stafffinder.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService {

    private static final int PASSWORD_MAX_LENGTH = 255;

    @Override
    public String hashFor(String string) {
        String hash = DigestUtils.md5Hex(string);
        if (hash.length() > PASSWORD_MAX_LENGTH) {
            hash = hash.substring(0, PASSWORD_MAX_LENGTH - 1);
        }
        return hash;
    }

}
