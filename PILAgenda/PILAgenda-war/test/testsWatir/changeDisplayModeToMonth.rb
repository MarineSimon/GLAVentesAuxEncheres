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
		sleep 2
	end
	def self.switch_to_month() 
		
		#puts $browser.html
		switchToMonth = $browser.button(:id, "j_idt8:month")
		switchToMonth.click
		sleep 2
	end
	def self.switch_to_day() 
		$browser.goto("http://localhost:8080/PILAgenda-war/faces/viewAgenda.xhtml") 
		sleep 1
		#puts $browser.html
		switchToDay = $browser.button(:id, "j_idt8:day")
		switchToDay.click
		sleep 2
	end

	def self.create_event()
		$browser.goto("http://localhost:8080/PILAgenda-war/faces/createEvent.xhtml") 
		nameEvent = $browser.text_field(:id, "j_idt9:createEvent:title")
		nameEvent.set("Evenement 2")
		descEvent = $browser.text_field(:id, "j_idt9:createEvent:description")
		descEvent.set("mon event personnel")
		beginEvent = $browser.text_field(:id, "j_idt9:createEvent:from_input")
		beginEvent.set("29/01/2013 18:57")
		endEvent = $browser.text_field(:id, "j_idt9:createEvent:to_input")
		endEvent.set("29/01/2013 20:57")
		bouton = $browser.button(:id, "j_idt9:createEvent:save")
		bouton.click
		sleep 1
	end
end

class TEST_EX1 < Test::Unit::TestCase
	def test_switch_to_month()
		EX1.init()
		EX1.connect()
		EX1.switch_to_month() 
		assert($browser.text.include?("Evenement 1"))
		puts "switch to month ok"
		EX1.create_event() 
		EX1.switch_to_month() 
		assert($browser.text.include?("Evenement 2"))
		assert($browser.text.include?("Evenement 1"))
		puts "create event ok"
		EX1.switch_to_day() 
		assert(!$browser.text.include?("Evenement 2"))
		assert($browser.text.include?("Evenement 1"))
		puts "switch to day ok"
		EX1.switch_to_month() 
		#$browser.close
	end
end

