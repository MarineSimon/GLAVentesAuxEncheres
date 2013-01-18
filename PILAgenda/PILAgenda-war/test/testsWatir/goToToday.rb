require 'rubygems'
require 'watir-webdriver'
require 'test/unit'

$browser
class EX1 
	def self.init()
		$browser = Watir::Browser.new :chrome
	end
	def self.connect()
		$browser.goto("http://localhost:8080/PILAgenda-war/faces/login.xhtml") 
		login = $browser.text_field(:id, "j_idt9:formLogin:j_idt20")
		login.set("admin")
		mdp = $browser.text_field(:id, "j_idt9:formLogin:j_idt22")
		mdp.set("admin")
		bouton = $browser.button(:id, "j_idt9:formLogin:btnLogin")
		bouton.click
		sleep 1
	end
	
	def self.goToNextDay() 
		nextB = $browser.button(:id, "j_idt8:next")
		nextB.click
		sleep 1
	end
	
	def self.goToToday() 
		today = $browser.button(:id, "j_idt8:today")
		today.click
		sleep 1
	end

	def self.create_event()
		$browser.goto("http://localhost:8080/PILAgenda-war/faces/createEvent.xhtml") 
		nameEvent = $browser.text_field(:id, "j_idt9:createEvent:title")
		nameEvent.set("Evenement 2")
		descEvent = $browser.text_field(:id, "j_idt9:createEvent:description")
		descEvent.set("mon event personnel")
		beginEvent = $browser.text_field(:id, "j_idt9:createEvent:from_input")
		beginEvent.set("17/01/2013 18:57")
		endEvent = $browser.text_field(:id, "j_idt9:createEvent:to_input")
		endEvent.set("17/01/2013 20:57")
		bouton = $browser.button(:id, "j_idt9:createEvent:save")
		bouton.click
		sleep 1
	end
end

class TEST_EX1 < Test::Unit::TestCase
	def test_switch_to_month()
		EX1.init()
		EX1.connect()
		assert($browser.text.include?("Evenement 1"))
		puts "connect ok"
		EX1.create_event() 
		EX1.goToNextDay()
		assert($browser.text.include?("Evenement 2"))
		assert(!$browser.text.include?("Evenement 1"))
		puts "create event ok"
		EX1.goToToday()
		assert(!$browser.text.include?("Evenement 2"))
		assert($browser.text.include?("Evenement 1"))
		puts "go to today ok"
	end
end

