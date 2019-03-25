package afomic.com.camfood.ui.createFood;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.helper.FirebaseFileService;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class CreateFoodPresenterTest {
    @Mock
    CreateFoodView createFoodView;
    @Mock
    AuthManager authManager;
    @Mock
    FirebaseFileService fileService;

    @Before
    public void setup(){

    }


}