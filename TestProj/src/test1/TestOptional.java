package test1;

import java.util.*;

public class TestOptional {

	public static void main(String[] args) {
		Optional<Computer2> computer = Optional.of(new Computer2());
		Optional<Soundcard> sc = Optional.empty(); 

		Soundcard soundcard = new Soundcard();
		Optional<Soundcard> sc2 = Optional.of(soundcard); 

		Optional<Soundcard> sc3 = Optional.ofNullable(soundcard);
		
		sc2.ifPresent(System.out::println);
		sc2.ifPresent(n->System.out.println(n.getUSB()));
		
		if(sc2.isPresent()) {
			System.out.println(sc2.get());
		}
		
		if(sc2.get().getUSB().isPresent()) {
			System.out.println(sc2.get().getUSB().isPresent());
		}

		String version = sc2.flatMap(Soundcard::getUSB)
                .map(USB::getVersion)
                .orElse("UNKNOWN");
		
		System.out.println(version);

		
		Optional<String> version2 = sc2.flatMap(Soundcard::getUSB)
                .map(USB::getVersion);
		
		
		System.out.println(version2.get());

		
		Optional<USB> maybeUSB = Optional.ofNullable(new USB());
		Optional<USB> maybeVersion = maybeUSB.filter(u->"1.0".equals(u.getVersion()));
		
		System.out.println("maybeversion = "+maybeVersion.flatMap(USB::getVersion));
		
		Optional<Optional<Soundcard>> sco = computer.map(Computer2::getSoundcard);
		Optional<Soundcard> scof = computer.flatMap(Computer2::getSoundcard);
		
		System.out.println("Computer sound car version: "+new Computer2().getSoundcard().get());	
		
		version = computer.flatMap(Computer2::getSoundcard)
                .flatMap(Soundcard::getUSB)
                .map(USB::getVersion)
                .orElse("UNKNOWN");

	}

}

class Computer2 {
	  private Optional<Soundcard> soundcard ;  
	  public Optional<Soundcard> getSoundcard() { return soundcard;}
	  
	}

class Soundcard {
	  private Optional<USB> usb = Optional.ofNullable(new USB()); //Optional.empty();
	  public Optional<USB> getUSB() { return usb; }
	  @Override
	  public String toString() {return "Soundcard";}

	}

class USB{
	  public Optional<String> getVersion(){ return "1.0"; }
	}