package teste;

import static org.junit.Assert.*;
import main.Gara;
import memento.CareTaker;
import memento.Memento;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clase.IdGresitException;
import clase.Locomotiva;
import clase.NrLocuriNegativException;
import clase.Tren;
import clase.TrenBuilder;
import clase.Vagon;
import clase.Locomotiva.Type;

public class TestareGetteri {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Gara.getInstanta().getListaTrenuri().clear();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetIdLocomotiva() throws IdGresitException {
		Locomotiva l = new Locomotiva(31, Locomotiva.Type.DIESEL);
		assertEquals(31, l.getId());
		
	}
	
	@Test
	public void testGetTypeLocomotiva() throws IdGresitException {
		Locomotiva l = new Locomotiva(31, Locomotiva.Type.DIESEL);
		assertEquals(Type.DIESEL, l.getType());
		
	}
	
	@Test
	public void testGetNrLocuriVagon() throws IdGresitException, NrLocuriNegativException{
		Vagon vagon = new Vagon(55, Vagon.Type.CLASA1, 50);
		assertEquals(50, vagon.getNumarLocuri());
	}
	
	@Test
	public void testGetTypeVagon() throws IdGresitException, NrLocuriNegativException{
		Vagon vagon = new Vagon(55, Vagon.Type.CLASA1, 50);
		assertEquals(Vagon.Type.CLASA1, vagon.getType());
	}
	
	@Test
	public void testGetIdVagon() throws IdGresitException, NrLocuriNegativException{
		Vagon vagon = new Vagon(55, Vagon.Type.CLASA1, 50);
		assertEquals(55, vagon.getId());
	}
	
	
	@Test
	public void testGetTipTren() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren1 = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.build();
		assertNotNull(tren1.getType());
	}
	
	@Test
	public void testGetVagoane() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren1 = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.build();
		assertNotNull(tren1.getVagoane());
	}
	
	@Test
	public void testGetVagonCuIndex() throws IdGresitException, NrLocuriNegativException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren1 = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.addVagon(new Vagon(6, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(7, Vagon.Type.CLASA2, 50))
			.addVagon(new Vagon(8, Vagon.Type.RESTAURANT, 50))
			.build();
		assertNotNull(tren1.getVagoane().get(2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void getTrenNull(){
		Gara.getInstanta().getListaTrenuri().get(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void getVagonNull() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(5).build();
		tren.getVagoane().get(0);
	}
	
	
	@Test
	public void testTrenGetTypeNull() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(5).build();
		assertNull(tren.getType());
	}
	
	@Test
	public void testTrenGetLocomotivaNull() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(3).build();
		assertNull(tren.getLocomotiva());
	}
	
	@Test
	public void testTrenGetVagoaneNotNull() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(3).build();
		assertNotNull(tren.getVagoane());
	}
	
	@Test
	public void testListaTrenuriNotNull(){
		assertNotNull(Gara.getInstanta().getListaTrenuri());
	}

	
	@Test
	public void testGetterMemento() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(3).build();
		Memento m = new Memento(tren);
		
		assertEquals(tren, m.getTren());
	}
	
	@Test
	public void listaCareTakerNotNull(){
		CareTaker ct = new CareTaker();
		assertNotNull(ct.getListaMemento());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void getMementoNull(){
		CareTaker ct = new CareTaker();
		ct.getMemento(0);
	}
}
