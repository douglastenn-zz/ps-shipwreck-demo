package com.boot.controller;

import com.boot.dasboot.controller.ShipwreckController;
import com.boot.dasboot.model.Shipwreck;
import com.boot.dasboot.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShipwreckControllerTest {

    @InjectMocks
    private ShipwreckController shipwreckController;

    @Mock
    private ShipwreckRepository shipwreckRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckGet() {
        Shipwreck returnedShipwreck = new Shipwreck();
        returnedShipwreck.setId(1L);
        when(shipwreckRepository.findOne(1L)).thenReturn(returnedShipwreck);

        Shipwreck shipwreck = shipwreckController.get(1L);

        verify(shipwreckRepository).findOne(1L);
        assertThat(shipwreck.getId(), is(1L));
    }

    @Test
    public void testShipwreckGets() {
        Shipwreck returnedShipwreck1 = new Shipwreck();
        returnedShipwreck1.setId(1L);
        Shipwreck returnedShipwreck2 = new Shipwreck();
        returnedShipwreck2.setId(2L);
        List<Shipwreck> shipwreckList = new ArrayList<Shipwreck>();
        shipwreckList.add(returnedShipwreck1);
        shipwreckList.add(returnedShipwreck2);

        when(shipwreckRepository.findAll()).thenReturn(shipwreckList);

        List<Shipwreck> shipwrecks = shipwreckController.list();
        verify(shipwreckRepository).findAll();
        assertThat(shipwrecks.size(), is(2));
        assertThat(shipwrecks.get(0).getId(), is(1L));
        assertThat(shipwrecks.get(1).getId(), is(2L));
    }

    @Test
    public void testShipwreckCreate() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(1L);

        when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);

        Shipwreck returnedShipwreck = shipwreckController.create(shipwreck);

        verify(shipwreckRepository).saveAndFlush(returnedShipwreck);
        assertThat(returnedShipwreck.getId(), is(1L));
    }

    @Test
    public void testShipwreckUpdate() {
        Shipwreck foundShipwreck = new Shipwreck();
        foundShipwreck.setId(1L);

        Shipwreck updateShipwreck = new Shipwreck();
        updateShipwreck.setId(1L);
        updateShipwreck.setName("Test");

        when(shipwreckRepository.findOne(1L)).thenReturn(foundShipwreck);
        when(shipwreckRepository.saveAndFlush(foundShipwreck)).thenReturn(foundShipwreck);

        Shipwreck returnedShipwreck = shipwreckController.update(1L, updateShipwreck);

        verify(shipwreckRepository).findOne(1L);
        verify(shipwreckRepository).saveAndFlush(returnedShipwreck);
        assertThat(returnedShipwreck.getId(), is(1L));
        assertThat(returnedShipwreck.getName(), is("Test"));
    }

    @Test
    public void testShipwreckDelete() {
        Shipwreck foundShipwreck = new Shipwreck();
        foundShipwreck.setId(1L);
        when(shipwreckRepository.findOne(1L)).thenReturn(foundShipwreck);

        Shipwreck deletedShipwreck = shipwreckController.delete(1L);

        verify(shipwreckRepository).delete(foundShipwreck);
        assertThat(deletedShipwreck.getId(), is(1L));
    }

}