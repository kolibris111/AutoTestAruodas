import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AutoTestAruodas {

    WebDriver _globalDriver;
    String _email;
    String _password;

    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate password part
        int passwordLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < passwordLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }

        // Adding '@' symbol
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }
    public String generatePassword() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder password = new StringBuilder();

        // Generate password part
        int passwordLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < passwordLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            password.append(randomChar);

        }
        return password.toString();
    }
    @BeforeClass
    public void SetupUserDetails() {
        _email = generateRandomEmail();
        _password = generatePassword();
    }

    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        _globalDriver = new ChromeDriver(options);
        _globalDriver.get("https://www.aruodas.lt/");
        _globalDriver.manage().window().maximize();
    }
    @Test
    public void Test1(){
        //Registravimasis teisingais duomenimis
        _globalDriver.findElement(By.id("Conetrust-accept-btn-handler")).click();//Patvirtiname slapukus//
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div")).click(); //Spaudžiama prisijungti
        _globalDriver.findElement(By.id("popup__right__register__container")).click(); //Pasirenkama registruotis
        _globalDriver.findElement(By.id("userName")).sendKeys("pukismiau123@inbox.lt"); //Ivedamas el.pastas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div[2]/div/div/form/div[2]")).sendKeys("FiktyviAnketa123"); //Ivedamas slaptazodis
        _globalDriver.findElement(By.id("registerButton")).click(); //Registruotis

        _globalDriver.close();
    }
    @Test
    public void Test2(){
        //Registravimasis įvedant random el.paštą ir slaptažodį
        _globalDriver.findElement(By.id("Conetrust-accept-btn-handler")).click();//Patvirtiname slapukus//
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div")).click(); //Spaudžiama prisijungti
        _globalDriver.findElement(By.id("popup__right__register__container")).click(); //Pasirenkama registruotis
        _globalDriver.findElement(By.id("userName")).sendKeys(_email); //Ivedamas el.pastas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div[2]/div/div/form/div[2]")).sendKeys(_email); //Ivedamas slaptazodis
        _globalDriver.findElement(By.id("registerButton")).click(); //Registruotis

        _globalDriver.close();
    }
    @Test
    public void Test3(){
        //Prisijungimas prie paskyros teisingais duomenimis įkelti skelbimui
        _globalDriver.findElement(By.id("onetrust-accept-btn-handler")).click();//Patvirtiname slapukus//
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div")).click(); //Spaudžiama prisijungti
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.id("userName")).sendKeys("pukismiau123@inbox.lt"); //Ivedamas el.pastas
        _globalDriver.findElement(By.id("password")).sendKeys("FiktyviAnketa123"); //Ivedamas slaptazodis
        _globalDriver.findElement(By.id("loginAruodas")).click(); //Prisijungti

        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div/a")).click(); //Paspausti ideti skelbimą
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[1]/ul/li[1]")).click(); //Pasirinkti Butas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[2]/ul/li[1]")).click(); //Pasirinkti Pardavimui
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/span")).click(); //Pasirinkti Savivaldybe
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/ul[2]/li[8]")).click(); //Pasirinkti Palanga
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[4]/span[1]/span")).click(); //Pasirinkti Gyvenviete
        _globalDriver.findElement(By.id("districtTitle")).click(); //Pasirinkti Palangos m.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[5]/span[1]/span[2]")).click(); //Pasirinkti Mikrorajonas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[5]/span[1]/ul[5]/li")).click(); //Pasirinkti Sventoji
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/span[2]")).click(); //Pasirinkti Gatve
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/ul[5]/li[1]/input")).sendKeys(Keys.ARROW_DOWN); //Išskleisti sarasa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/ul[5]/li[36]")).click(); //Pasirinkti Bijunu tak.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[7]/span[1]/input")).sendKeys("14"); //Irasomas nr.
        _globalDriver.findElement(By.id("fieldFAreaOverAll")).sendKeys("0"); //Irasomas Plotas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[15]/div/div[1]")).click(); //Parenkamas kambariu skaicius 1
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[1]/span[2]/input")).sendKeys("1"); //Irasomas aukstu skaicius
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/input")).sendKeys("1"); //Irasomas aukstu skaicius is 1
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/input")).sendKeys(Keys.ARROW_DOWN); //Išskleisti sarasa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[17]/div[1]/span[1]/span/input")).sendKeys("1999"); //Irasomi statybos metai
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[18]/div/div[8]")).click(); //Pasirinkti pastato tipa Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[19]/div/div[6]")).click(); //Pasirinkti irengimas Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[21]/div/div[10]/label/span")).click(); //Pasirinkti sildymas Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[22]/div/div[2]/div[2]")).click(); //Pasirinkti tipa Patalpa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[23]/div/div[2]/div[2]")).click(); //Pasirinkti buto paskirtis poilsio
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[40]/div/div[1]/textarea")).sendKeys("NETIKRAS SKELBIMAS"); //Irasomas skelbimo aprasymas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[42]/div/div[1]/a/input")).sendKeys("C:\\Users\\palub\\OneDrive\\Paveikslėliai\\Namukas.jpg"); //Pasirinkti ikelti nuotrauka
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.id("priceField")).sendKeys("0"); //Irasoma kaina
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[48]/span[1]/input")).sendKeys("+37063586485"); //Irasomas tel.nr.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[52]/span[1]/div/div/label/span")).click(); //Sutikti su portalo taisyklemis
        _globalDriver.findElement(By.id("submitFormButton")).click(); //Paspausti ivesti skelbima

        _globalDriver.close();
    }
    @Test
    public void Test4(){
        //Skelbimo įkėlimas neprisijungus/neprisiregistravus ir nurodant netikrą el.paštą
        _globalDriver.findElement(By.id("onetrust-accept-btn-handler")).click();//Patvirtiname slapukus//
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div/a")).click(); //Paspausti ideti skelbimą
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[2]/ul/li[1]")).click(); //Pasirinkti Butas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[3]/ul/li[1]")).click(); //Pasirinkti Pardavimui
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/span")).click(); //Pasirinkti Savivaldybe
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/ul[2]/li[8]")).click(); //Pasirinkti Palanga
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[4]/span[1]/span")).click(); //Pasirinkti Gyvenviete
        _globalDriver.findElement(By.id("districtTitle")).click(); //Pasirinkti Palangos m.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[5]/span[1]/span[2]")).click(); //Pasirinkti Mikrorajonas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[5]/span[1]/ul[5]/li")).click(); //Pasirinkti Sventoji
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/span[2]")).click(); //Pasirinkti Gatve
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/ul[5]/li[1]/input")).sendKeys(Keys.ARROW_DOWN); //Išskleisti sarasa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/ul[5]/li[36]")).click(); //Pasirinkti Bijunu tak.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[7]/span[1]/input")).sendKeys("14"); //Irasomas nr.
        _globalDriver.findElement(By.id("fieldFAreaOverAll")).sendKeys("1"); //Irasomas Plotas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[15]/div/div[1]")).click(); //Parenkamas kambariu skaicius 1
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[1]/span[2]/input")).sendKeys("1"); //Irasomas aukstu skaicius
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/input")).sendKeys("1"); //Irasomas aukstu skaicius is 1
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/input")).sendKeys(Keys.ARROW_DOWN); //Išskleisti sarasa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[17]/div[1]/span[1]/span/input")).sendKeys("1999"); //Irasomi statybos metai
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[18]/div/div[8]")).click(); //Pasirinkti pastato tipa Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[19]/div/div[6]")).click(); //Pasirinkti irengimas Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[21]/div/div[10]/label/span")).click(); //Pasirinkti sildymas Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[22]/div/div[2]/div[2]")).click(); //Pasirinkti tipa Patalpa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[23]/div/div[2]/div[2]")).click(); //Pasirinkti buto paskirtis poilsio
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[40]/div/div[1]/textarea")).sendKeys("NETIKRAS SKELBIMAS"); //Irasomas skelbimo aprasymas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[42]/div/div[1]/a/input")).sendKeys("C:\\Users\\palub\\OneDrive\\Paveikslėliai\\Namukas.jpg"); //Pasirinkti ikelti nuotrauka
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.id("priceField")).sendKeys("1"); //Irasoma kaina
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[48]/span[1]/input")).sendKeys("63586485"); //Irasomas tel.nr.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[49]/span[1]/input")).sendKeys("pukismiau12345@inbox.lt"); //Irasomas el.pastas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[52]/span[1]/div/div/label/span")).click(); //Sutikti su portalo taisyklemis
        _globalDriver.findElement(By.id("submitFormButton")).click(); //Paspausti ivesti skelbima

        _globalDriver.close();
    }
    @Test
    public void Test5(){
        //Prisijungimas prie paskyros teisingais duomenimis įkelti skelbimui
        _globalDriver.findElement(By.id("onetrust-accept-btn-handler")).click();//Patvirtiname slapukus//
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div")).click(); //Spaudžiama prisijungti
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.id("userName")).sendKeys("pukismiau123@inbox.lt"); //Ivedamas el.pastas
        _globalDriver.findElement(By.id("password")).sendKeys("FiktyviAnketa123"); //Ivedamas slaptazodis
        _globalDriver.findElement(By.id("loginAruodas")).click(); //Prisijungti

        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div/a")).click(); //Paspausti ideti skelbimą
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[1]/ul/li[1]")).click(); //Pasirinkti Butas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[2]/ul/li[1]")).click(); //Pasirinkti Pardavimui
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/span")).click(); //Pasirinkti Savivaldybe
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/ul[2]/li[8]")).click(); //Pasirinkti Palanga
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[4]/span[1]/span")).click(); //Pasirinkti Gyvenviete
        _globalDriver.findElement(By.id("districtTitle")).click(); //Pasirinkti Palangos m.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[5]/span[1]/span[2]")).click(); //Pasirinkti Mikrorajonas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[5]/span[1]/ul[5]/li")).click(); //Pasirinkti Sventoji
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/span[2]")).click(); //Pasirinkti Gatve
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/ul[5]/li[1]/input")).sendKeys(Keys.ARROW_DOWN); //Išskleisti sarasa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[6]/span[1]/ul[5]/li[36]")).click(); //Pasirinkti Bijunu tak.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[7]/span[1]/input")).sendKeys("14"); //Irasomas nr.
        _globalDriver.findElement(By.id("fieldFAreaOverAll")).sendKeys("62"); //Irasomas Plotas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[15]/div/div[1]")).click(); //Parenkamas kambariu skaicius 1
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[1]/span[2]/input")).sendKeys("1"); //Irasomas aukstu skaicius
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/input")).sendKeys("1"); //Irasomas aukstu skaicius is 1
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/input")).sendKeys(Keys.ARROW_DOWN); //Išskleisti sarasa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[17]/div[1]/span[1]/span/input")).sendKeys("1999"); //Irasomi statybos metai
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[18]/div/div[8]")).click(); //Pasirinkti pastato tipa Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[19]/div/div[6]")).click(); //Pasirinkti irengimas Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[21]/div/div[10]/label/span")).click(); //Pasirinkti sildymas Kita
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[22]/div/div[2]/div[2]")).click(); //Pasirinkti tipa Patalpa
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[23]/div/div[2]/div[2]")).click(); //Pasirinkti buto paskirtis poilsio
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[40]/div/div[1]/textarea")).sendKeys("NETIKRAS SKELBIMAS"); //Irasomas skelbimo aprasymas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[42]/div/div[1]/a/input")).sendKeys("C:\\Users\\palub\\OneDrive\\Paveikslėliai\\Namukas.jpg"); //Pasirinkti ikelti nuotrauka
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.id("priceField")).sendKeys("1 000"); //Irasoma kaina
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[48]/span[1]/input")).sendKeys("+37063586485"); //Irasomas tel.nr.
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[52]/span[1]/div/div/label/span")).click(); //Sutikti su portalo taisyklemis
        _globalDriver.findElement(By.id("submitFormButton")).click(); //Paspausti ivesti skelbima

        _globalDriver.close();
    }
    @Test
    public void Test6(){
        //Surasti įkeltą skelbimą neprisijungus
        _globalDriver.findElement(By.id("onetrust-accept-btn-handler")).click();//Patvirtiname slapukus//
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.id("display_obj")).click(); //Aktyvuojamas objekto tipas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[1]/div/div/div/div/ul/li[1]/label")).click(); //Parenkamas Butas pardavimui
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[2]/div/div/div/span[2]")).click(); //Isskleidziamas savivaldybiu sarasas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div/ul/li[8]/label")).click(); //Parenkama Palanga
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[4]/div/a")).click(); //Pasirenkame akttyvuoti mikrorajona
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.switchTo().frame("fancybox-frame");
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/label[3]/input")).click();//Pasirenkamas mikrorajonas Sventoji
        _globalDriver.findElement(By.id("btSaveSelected")).click(); //Spaudziama issaugoti
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.switchTo().defaultContent();

        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[5]/div/a/span[2]")).click(); //Isskleidziamas gatviu sarasas
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.switchTo().frame("fancybox-frame");
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/input")).sendKeys("Bangų g."); //Paieskoje irasome gatves pavadinima
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/label[31]/input")).click(); //Pasirenkama gatve
        _globalDriver.findElement(By.id("btSaveSelected")).click(); //Spaudziama issaugoti
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _globalDriver.switchTo().defaultContent();

        _globalDriver.findElement(By.id("input_FAreaOverAllMin")).sendKeys("1"); //Irasomas plotas nuo
        _globalDriver.findElement(By.id("input_FAreaOverAllMax")).sendKeys("62"); //Irasomas plotas iki
        _globalDriver.findElement(By.id("quickValue_FRoomNum_1")).click(); //Pazymimas kambariu skaicius 1
        _globalDriver.findElement(By.id("display_text_FHouseState")).click(); //Iskleidziams Irengimo sarasas
        _globalDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[2]/div[3]/div/div/div/ul/li[1]/label/input")).click(); //Pasirenkama buto irengimo savybe Irengtas
        _globalDriver.findElement(By.id("input_FPriceMin")).sendKeys("1000"); //Irasoma kaina nuo
        _globalDriver.findElement(By.id("input_FPriceMax")).sendKeys("2000"); //Irasoma kaina iki
        _globalDriver.findElement(By.id("buttonSearchForm")).click(); //Spaudziama ieskoti






    }





}
