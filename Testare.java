package teste;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import main.Gara;
import memento.CareTaker;
import memento.Memento;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clase.IdGresitException;
import clase.ListaTrenuriGoalaException;
import clase.Locomotiva;
import clase.Locomotiva.Type;
import clase.NrLocuriNegativException;
import clase.Tren;
import clase.TrenBuilder;
import clase.Vagon;
import clase.VagonulRestaurantNuCereBilet;

public class Testare {

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
	public void testTrenBuilder() throws IdGresitException{
		Locomotiva locomotiva = new Locomotiva(3, Locomotiva.Type.ELECTRICA);
		
		TrenBuilder builder = new TrenBuilder();
		builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(locomotiva);
		
		Tren tren = builder.build();
		Tren tren2 = new Tren(1, Tren.Type.ACCELERAT, locomotiva);
		
		assertEquals(tren2, tren);
	}
	
	@Test
	public void testTrenBuilderCuVagoane() throws IdGresitException, NrLocuriNegativException{
		Locomotiva locomotiva = new Locomotiva(3, Locomotiva.Type.ELECTRICA);
		
		Vagon v1 = new Vagon(1, Vagon.Type.CLASA1, 40);
		Vagon v2 = new Vagon(2, Vagon.Type.CLASA1, 40);
		Vagon v3 = new Vagon(3, Vagon.Type.CLASA2, 80);
		
		TrenBuilder builder = new TrenBuilder();
		builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(locomotiva)
			.addVagon(v1)
			.addVagon(v2)
			.addVagon(v3);
		Tren tren = builder.build();
		
		ArrayList<Vagon> list = new ArrayList<>();
		list.add(v1);
		list.add(v2);
		list.add(v3);
		
		assertEquals(list, tren.getVagoane());
	}
	
	@Test
	public void testTrenBuilderCuVagoane2() throws IdGresitException, NrLocuriNegativException{
		Locomotiva locomotiva = new Locomotiva(3, Locomotiva.Type.ELECTRICA);
		
		Vagon v1 = new Vagon(1, Vagon.Type.CLASA1, 40);
		Vagon v2 = new Vagon(2, Vagon.Type.CLASA1, 40);
		Vagon v3 = new Vagon(3, Vagon.Type.CLASA2, 80);
		
		Vagon v4 = new Vagon(4, Vagon.Type.RESTAURANT, 22);
		Vagon v5 = new Vagon(5, Vagon.Type.RESTAURANT, 22);
		ArrayList<Vagon> vagoaneRestaurant = new ArrayList<>();
		vagoaneRestaurant.add(v4);
		vagoaneRestaurant.add(v5);
		
		TrenBuilder builder = new TrenBuilder();
		builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(locomotiva)
			.addVagon(v1)
			.addVagon(v2)
			.addVagon(v3)
			.addVagoane(vagoaneRestaurant);
		Tren tren = builder.build();
		
		ArrayList<Vagon> list = new ArrayList<>();
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		list.add(v5);
		
		assertEquals(list, tren.getVagoane());
	}
	
	@Test
	public void testTrenAdaugat() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		
		assertTrue(Gara.getInstanta().adaugaTren(tren));
	}
	
	@Test
	public void testTrenAdaugareCuAcelasiId() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1).build();
		Gara.getInstanta().adaugaTren(tren);
		
		builder = new TrenBuilder();
		tren = builder.setId(1).build();
		assertFalse(Gara.getInstanta().adaugaTren(tren));
	}
	
	@Test
	public void testTrenAdaugatPentru2TrenuriCuIdDiferit() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren);
		
		builder = new TrenBuilder();
		tren = builder.setId(2)
				.setType(Tren.Type.RAPID)
				.setLocomotiva(new Locomotiva(22, Locomotiva.Type.ELECTRICA)).build();
		
		assertTrue(Gara.getInstanta().adaugaTren(tren));
	}
	
	
	@Test
	public void testStergereTrenReusita() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren);
		
		builder = new TrenBuilder();
		tren = builder.setId(2)
				.setType(Tren.Type.RAPID)
				.setLocomotiva(new Locomotiva(22, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren);
		
		assertTrue(Gara.getInstanta().stergeTren(2));
	}
	
	@Test
	public void testStergereTrenNereusita() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren);
		
		builder = new TrenBuilder();
		tren = builder.setId(2)
				.setType(Tren.Type.RAPID)
				.setLocomotiva(new Locomotiva(22, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren);
		
		assertFalse(Gara.getInstanta().stergeTren(10000));
	}
	
	@Test
	public void testCelMaiLungTren() throws IdGresitException, NrLocuriNegativException, ListaTrenuriGoalaException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren1 = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.addVagon(new Vagon(1, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(2, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(3, Vagon.Type.CLASA2, 50))
			.addVagon(new Vagon(4, Vagon.Type.RESTAURANT, 50))
			.build();
		Gara.getInstanta().adaugaTren(tren1);
		
		builder = new TrenBuilder();
		Tren tren2 = builder.setId(2)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.addVagon(new Vagon(5, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(6, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(7, Vagon.Type.CLASA2, 50))
			.addVagon(new Vagon(8, Vagon.Type.RESTAURANT, 50))
			.addVagon(new Vagon(9, Vagon.Type.CUSETA, 50))
			.addVagon(new Vagon(10, Vagon.Type.CUSETA, 50))
			.build();
		Gara.getInstanta().adaugaTren(tren2);
		
		builder = new TrenBuilder();
		Tren tren3 = builder.setId(3)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren3);
		
		assertEquals(tren2, Gara.getInstanta().getCelMaiLungTren());
	}
	
	@Test(expected=ListaTrenuriGoalaException.class)
	public void testCelMaiLungTrenListaGoala() throws ListaTrenuriGoalaException{
		assertEquals(new ArrayList<Tren>(), Gara.getInstanta().getCelMaiLungTren());
	}

	@Test
	public void testCelMaiScurtTren() throws IdGresitException, NrLocuriNegativException, ListaTrenuriGoalaException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren1 = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.addVagon(new Vagon(1, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(2, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(3, Vagon.Type.CLASA2, 50))
			.addVagon(new Vagon(4, Vagon.Type.RESTAURANT, 50))
			.build();
		Gara.getInstanta().adaugaTren(tren1);
		
		builder = new TrenBuilder();
		Tren tren2 = builder.setId(2)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.addVagon(new Vagon(5, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(6, Vagon.Type.CLASA1, 50))
			.addVagon(new Vagon(7, Vagon.Type.CLASA2, 50))
			.addVagon(new Vagon(8, Vagon.Type.RESTAURANT, 50))
			.addVagon(new Vagon(9, Vagon.Type.CUSETA, 50))
			.addVagon(new Vagon(10, Vagon.Type.CUSETA, 50))
			.build();
		Gara.getInstanta().adaugaTren(tren2);
		
		builder = new TrenBuilder();
		Tren tren3 = builder.setId(3)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		Gara.getInstanta().adaugaTren(tren3);
		
		assertEquals(tren3, Gara.getInstanta().getCelMaiScurtTren());
	}
	
	@Test(expected=ListaTrenuriGoalaException.class)
	public void testCelMaiScurtTrenListaGoala() throws ListaTrenuriGoalaException{
		assertEquals(new ArrayList<Tren>(), Gara.getInstanta().getCelMaiScurtTren());
	}

	
	@Test
	public void testGetLocomotivaNotNull() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren1 = builder.setId(1)
			.setType(Tren.Type.ACCELERAT)
			.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA))
			.build();
		assertNotNull(tren1.getLocomotiva());
	}
	
	
	@Test
	public void testMomento() throws IdGresitException, NrLocuriNegativException{
		CareTaker careTaker = new CareTaker();
		Memento memento;
		
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
				.setType(Tren.Type.ACCELERAT)
				.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		tren.addVagon(new Vagon(2, Vagon.Type.RESTAURANT, 20));
		tren.addVagon(new Vagon(3, Vagon.Type.CLASA1, 30));
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		tren.addVagon(new Vagon(4, Vagon.Type.CLASA2, 50));
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		assertEquals(2, careTaker.getMemento(1).getTren().getVagoane().size());
	}
	
	@Test
	public void testMomento2() throws IdGresitException{
		CareTaker careTaker = new CareTaker();
		Memento memento;
		
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
				.setType(Tren.Type.ACCELERAT)
				.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		tren.setLocomotiva(new Locomotiva(44, Locomotiva.Type.DIESEL));
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		tren.setLocomotiva(new Locomotiva(11, Locomotiva.Type.DIESEL));
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		assertEquals(44, careTaker.getMemento(1).getTren().getLocomotiva().getId());
	}
	
	@Test
	public void testMomento3() throws IdGresitException{
		CareTaker careTaker = new CareTaker();
		Memento memento;
		
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(1)
				.setType(Tren.Type.ACCELERAT)
				.setLocomotiva(new Locomotiva(33, Locomotiva.Type.ELECTRICA)).build();
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		tren.setType(Tren.Type.INTERCITY);
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		tren.setType(Tren.Type.RAPID);
		memento = new Memento(tren);
		careTaker.addMemento(memento);
		
		assertEquals(Tren.Type.INTERCITY, careTaker.getMemento(1).getTren().getType());
	}
	
	@Test(expected=IdGresitException.class)
	public void testTrenGetIdNull() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.build();
	}
	
	@Test(expected=IdGresitException.class)
	public void testIdGresitLocomotiva() throws IdGresitException{
		Locomotiva l = new Locomotiva(-430, Locomotiva.Type.ELECTRICA);
	}
	
	@Test(expected=IdGresitException.class)
	public void testIdGresitTrena() throws IdGresitException{
		TrenBuilder builder = new TrenBuilder();
		Tren tren = builder.setId(-100).build();
	}
	
	@Test(expected=IdGresitException.class)
	public void testIdGresitVagon() throws IdGresitException, NrLocuriNegativException{
		Vagon v = new Vagon(-333, Vagon.Type.CLASA1, 22);
	}
	
	
	
	@Test
	public void testPretClasa1() throws IdGresitException, NrLocuriNegativException, VagonulRestaurantNuCereBilet{
		Vagon vagon = new Vagon(44, Vagon.Type.CLASA1, 50);
		assertEquals(150, vagon.stabilestePretVagon(), 0);
	}
	
	@Test
	public void testPretClasa2() throws IdGresitException, NrLocuriNegativException, VagonulRestaurantNuCereBilet{
		Vagon vagon = new Vagon(44, Vagon.Type.CLASA2, 50);
		assertEquals(100, vagon.stabilestePretVagon(), 0);
	}
	
	@Test
	public void testPretCuseta() throws IdGresitException, NrLocuriNegativException, VagonulRestaurantNuCereBilet{
		Vagon vagon = new Vagon(44, Vagon.Type.CUSETA, 50);
		assertEquals(200, vagon.stabilestePretVagon(), 0);
	}
	
	@Test(expected=VagonulRestaurantNuCereBilet.class)
	public void testPretRestaurant() throws IdGresitException, NrLocuriNegativException, VagonulRestaurantNuCereBilet{
		Vagon vagon = new Vagon(44, Vagon.Type.RESTAURANT, 50);
		vagon.stabilestePretVagon();
	}
	
	@Test
	public void testPretZeroLocuri() throws IdGresitException, NrLocuriNegativException, VagonulRestaurantNuCereBilet{
		Vagon vagon = new Vagon(44, Vagon.Type.CLASA1, 0);
		assertEquals(0, vagon.stabilestePretVagon(), 0);
	}
	
	@Test
	public void addVagoaneDiferiteLaTrenuriDiferite() throws IdGresitException, NrLocuriNegativException{
		Vagon vagon1 = new Vagon(12, Vagon.Type.CLASA1, 60);
		Vagon vagon2 = new Vagon(13, Vagon.Type.CLASA2, 120);
		
		TrenBuilder builder1 = new TrenBuilder();
		Tren tren1 = builder1.setId(3).build();
		Gara.getInstanta().adaugaTren(tren1);
		
		TrenBuilder builder2 = new TrenBuilder();
		Tren tren2 = builder2.setId(4).build();
		Gara.getInstanta().adaugaTren(tren2);
		
		tren1.addVagon(vagon1);
		assertTrue(tren2.addVagon(vagon2));
	}
	
	@Test
	public void addAcelasiVagonLaTrenuriDiferite() throws IdGresitException, NrLocuriNegativException{
		Vagon vagon1 = new Vagon(12, Vagon.Type.CLASA1, 60);
		
		TrenBuilder builder1 = new TrenBuilder();
		Tren tren1 = builder1.setId(3).build();
		Gara.getInstanta().adaugaTren(tren1);
		
		TrenBuilder builder2 = new TrenBuilder();
		Tren tren2 = builder2.setId(4).build();
		Gara.getInstanta().adaugaTren(tren2);
		
		tren1.addVagon(vagon1);
		assertFalse(tren2.addVagon(vagon1));
	}
	
	@Test
	public void addAcelasiVagonLaAcelasiTreb() throws IdGresitException, NrLocuriNegativException{
		Vagon vagon1 = new Vagon(12, Vagon.Type.CLASA1, 60);
		
		TrenBuilder builder1 = new TrenBuilder();
		Tren tren1 = builder1.setId(3).build();
		Gara.getInstanta().adaugaTren(tren1);
		
		tren1.addVagon(vagon1);
		assertFalse(tren1.addVagon(vagon1));
	}
	
	@Test
	public void testCareTakerMockito() throws IdGresitException{
		CareTaker ct = new CareTaker();
		Memento m;
		
		Tren t = mock(Tren.class);
		when(t.getId()).thenReturn(1);
		when(t.getType()).thenReturn(Tren.Type.ACCELERAT);
		m = new Memento(t);
		ct.addMemento(m);
		
		when(t.getType()).thenReturn(Tren.Type.RAPID);
		m = new Memento(t);
		ct.addMemento(m);
		
		assertEquals(Tren.Type.ACCELERAT, ct.getMemento(0).getTren().getType());
	}
	
	@Test
	public void testAdaugaTrenReusitMockito(){
		Tren t = mock(Tren.class);
		when(t.getId()).thenReturn(1);
		
		assertTrue(Gara.getInstanta().adaugaTren(t));
	}
	
	@Test
	public void testAdaugaTrenNereusitMockito(){
		Tren t = mock(Tren.class);
		when(t.getId()).thenReturn(1);
		
		Tren t2 = mock(Tren.class);
		when(t2.getId()).thenReturn(1);
		
		Gara.getInstanta().adaugaTren(t);
		assertFalse(Gara.getInstanta().adaugaTren(t2));
	}


}
