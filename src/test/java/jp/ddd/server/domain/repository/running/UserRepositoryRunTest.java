package jp.ddd.server.domain.repository.running;

import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.domain.repository.UserRepository;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by noguchi_kohei 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class UserRepositoryRunTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findTest() {
        try {
            val results = userRepository.findAll();

            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
}
