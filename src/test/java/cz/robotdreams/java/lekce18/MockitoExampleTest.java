package cz.robotdreams.java.lekce18;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MockitoExampleTest {


    @Test
    public void testList() {
        List mockedList = mock(List.class);
        when(mockedList.size()).thenReturn(3);
        assertEquals(3, mockedList.size());

//        when(mockedList.get(15)).thenReturn("ahoj");
//        assertEquals("aho", mockedList.get(15));


//        verify(mockedList).get(16);


//        doThrow(new RuntimeException("Not implemented")).when(mockedList).remove(any());
//        Exception exceptionResult = assertThrows(RuntimeException.class, () ->  mockedList.remove("aaa"));
//        assertEquals("Not implemented", exceptionResult );


    }






}
