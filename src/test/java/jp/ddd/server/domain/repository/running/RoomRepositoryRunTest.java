package jp.ddd.server.domain.repository.running;

import jp.ddd.server.domain.repository.RoomRepository;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

/**
 * Created by noguchi_kohei 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class RoomRepositoryRunTest {
    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getTest() {
        try {
            val results = roomRepository.findByDtDesc(1);

            System.out.println(results);
        } catch (Exception e) {
            fail();
        }
    }
}
