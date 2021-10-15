package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import com.nico5310.assessmentMicroservice.model.Assessments;
import com.nico5310.assessmentMicroservice.proxies.NoteMSProxy;
import com.nico5310.assessmentMicroservice.proxies.PatientMSProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AssessmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AssessmentServiceImplTest {

    @MockBean
    private AgeCalculator ageCalculator;

    @Autowired
    private AssessmentServiceImpl assessmentServiceImpl;

    @MockBean
    private NoteMSProxy noteMSProxy;

    @MockBean
    private PatientMSProxy patientMSProxy;

    @MockBean
    private TriggersCalculator triggersCalculator;

    @Test
    void testGetDiabetesLevelRisk() {

        when(this.triggersCalculator.calculateTriggersInNotes((Integer) any())).thenReturn(1);
        when(this.patientMSProxy.getById((Integer) any())).thenReturn(new PatientBean());
        when(this.ageCalculator.ageCalculate((java.time.LocalDate) any())).thenReturn(1);
        assertEquals(Assessments.NONE, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
        verify(this.triggersCalculator).calculateTriggersInNotes((Integer) any());
        verify(this.patientMSProxy).getById((Integer) any());
        verify(this.ageCalculator).ageCalculate((java.time.LocalDate) any());
    }

    @Test
    void testGetDiabetesLevelRisk2() {

        when(this.triggersCalculator.calculateTriggersInNotes((Integer) any())).thenReturn(1);
        when(this.patientMSProxy.getById((Integer) any())).thenReturn(new PatientBean());
        when(this.ageCalculator.ageCalculate((java.time.LocalDate) any())).thenReturn(0);
        assertEquals(Assessments.NONE, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
        verify(this.triggersCalculator).calculateTriggersInNotes((Integer) any());
        verify(this.patientMSProxy).getById((Integer) any());
        verify(this.ageCalculator).ageCalculate((java.time.LocalDate) any());
    }

    @Test
    void testGetDiabetesLevelRisk3() {

        when(this.triggersCalculator.calculateTriggersInNotes((Integer) any())).thenReturn(1);
        when(this.patientMSProxy.getById((Integer) any())).thenReturn(new PatientBean());
        when(this.ageCalculator.ageCalculate((java.time.LocalDate) any())).thenReturn(77);
        assertEquals(Assessments.NONE, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
        verify(this.triggersCalculator).calculateTriggersInNotes((Integer) any());
        verify(this.patientMSProxy).getById((Integer) any());
        verify(this.ageCalculator).ageCalculate((java.time.LocalDate) any());
    }

    @Test
    void testGetDiabetesLevelRisk4() {

        when(this.triggersCalculator.calculateTriggersInNotes((Integer) any())).thenReturn(7);
        when(this.patientMSProxy.getById((Integer) any())).thenReturn(new PatientBean());
        when(this.ageCalculator.ageCalculate((java.time.LocalDate) any())).thenReturn(77);
        assertEquals(Assessments.IN_DANGER, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
        verify(this.triggersCalculator).calculateTriggersInNotes((Integer) any());
        verify(this.patientMSProxy).getById((Integer) any());
        verify(this.ageCalculator).ageCalculate((java.time.LocalDate) any());
    }
}

