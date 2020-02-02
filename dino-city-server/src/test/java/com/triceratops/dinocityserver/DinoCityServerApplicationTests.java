package com.triceratops.dinocityserver;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.enums.DinoType;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DinoCityServerApplicationTests {

//	@Autowired
//	Dinos

    Dinosaur dino;
    Enclosure enclosure1;

	@Test
	void contextLoads() {

	}

    @Test
	public void dinoListStartsEmpty(){

        dino = new Dinosaur(DinoType.TRICERATOPS);
		enclosure1 = new Enclosure(SizeType.MEDIUM, SecurityLevel.MEDIUM);

		assertEquals(0,enclosure1.getDinosaurs().size());


	}

	@Test
	public void dinoStartsEmpty(){

        dino = new Dinosaur(DinoType.TRICERATOPS);
		enclosure1 = new Enclosure(SizeType.MEDIUM, SecurityLevel.MEDIUM);

		assertEquals(0,enclosure1.getDinosaurs().size());


	}
}
