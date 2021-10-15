package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.NoteBean;
import com.nico5310.assessmentMicroservice.proxies.NoteMSProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Test TriggersCalculator ")
@ContextConfiguration(classes = {TriggersCalculatorImpl.class})
@ExtendWith(SpringExtension.class)
class TriggersCalculatorImplTest {

    @MockBean
    private NoteMSProxy noteMSProxy;

    @Autowired
    private TriggersCalculatorImpl triggersCalculatorImpl;

    @Test
    void testCalculateTriggersInNotes() {

        when(noteMSProxy.listNote(any(Integer.class))).thenReturn(new ArrayList<NoteBean>());

        assertEquals(0,  triggersCalculatorImpl.calculateTriggersInNotes(1));

        verify(noteMSProxy, times(1)).listNote((any(Integer.class)));
    }



    @Test
    void testCalculateTriggersInNotesTest() {

        NoteBean note1 = new NoteBean("1", 1, LocalDate.now(), "Taille");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.now(), "Poids");
        List<NoteBean> listNote = new ArrayList<NoteBean>();
        listNote.add(note1);
        listNote.add(note2);
        List<String> triggersList = List.of("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");

        when(noteMSProxy.listNote(any(Integer.class))).thenReturn(listNote);

        assertEquals(2,  triggersCalculatorImpl.calculateTriggersInNotes(1));

        verify(noteMSProxy, times(1)).listNote((any(Integer.class)));
    }
}

