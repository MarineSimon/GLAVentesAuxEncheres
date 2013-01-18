# To change this template, choose Tools | Templates
# and open the template in the editor.

require 'rubygems'
require 'watir-webdriver'
require 'test/unit'

$browser
class Login 
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
  def self.add()
    t = $browser.img(:id,"addImg")
    t.click
    name = $browser.text_field(:id,"j_idt9:createAgenda:name")
    name.set("agendaTest")
    sleep 2
  end
end 
  
class TestCreateAgenda < Test::Unit::TestCase
  def test
    Login.init()
    Login.connect()
    Login.add()
    confirmer = $browser.button(:id,"j_idt9:createAgenda:saveButton")
    confirmer.click
    sleep 2
    title2 = $browser.url
    puts title2
    assert_equal("http://localhost:8080/PILAgenda-war/faces/viewAgenda.xhtml",title2)
    sleep 2
    assert($browser.text.include?("agendaTest"))
  end
end
