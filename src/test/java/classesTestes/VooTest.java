package classesTestes;

import classes.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;
import interfaces.ReservaPassagensDao;

public class VooTest {

	private static Voo voo;
	private static ReservaPassagensDao mockReservaDao;
	private final static int CAPACIDADE_VOO = 10;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		mockReservaDao = mock(ReservaPassagensDao.class);
		voo = new Voo(1234, "Aracaju", "Fortaleza", 
				Calendar.getInstance().getTime(), CAPACIDADE_VOO,
				(ReservaPassagensDao) mockReservaDao);
	}


	@After
	public void tearDown() throws Exception {
		reset(mockReservaDao);
	}

	// Teste caixa preta: Particionamento por Equivalencia

	 
	@Test
	public void testFazerReservaComSucesso() {
		Passageiro passageiro = new Passageiro("123123", "Fulano");
		//Passageiro passageiro = mock(Passageiro.class);
		List<Passagem> listaPassagem = new ArrayList<Passagem>();

		when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);

		when(mockReservaDao.getPassagem(passageiro, voo)).thenReturn(null);

		when(mockReservaDao.salvarPassagem((Passagem) notNull())).thenReturn(true);

		assertTrue(voo.fazerReserva(passageiro));
	}

	 
	@Test (expected = IllegalArgumentException.class)
	public void testeReservarSemTerPassageiro() {
		voo.fazerReserva(null);

	}

	 
	@Test
	public void testPassageiroComPassagemComprada() {
		Passageiro passageiro = mock(Passageiro.class);
		List <Passagem> listaPassagem = new ArrayList<Passagem> ();

		Passagem p = mock(Passagem.class);//new Passagem("11" , voo , passageiro);

		listaPassagem.add(p);
		when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);

		when(mockReservaDao.getPassagem(passageiro, voo)).thenReturn(p);

		when(mockReservaDao.salvarPassagem((Passagem) notNull())).thenReturn(false);

		assertFalse(voo.fazerReserva(passageiro));
	}


	@Test
	public void test() {
		Passageiro passageiro = mock(Passageiro.class);
		List<Passagem> listaPassagem = new ArrayList<Passagem>();
		Passagem p = mock(Passagem.class);
		
		for(int i = 0 ; i < 11 ; i++)
			listaPassagem.add(p);
		
		when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);

		when(mockReservaDao.getPassagem(passageiro, voo)).thenReturn(null);

		when(mockReservaDao.salvarPassagem((Passagem) notNull())).thenReturn(true);

		assertFalse(voo.fazerReserva(passageiro));
	}

}