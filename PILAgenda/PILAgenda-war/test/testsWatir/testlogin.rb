# To change this template, choose Tools | Templates
# and open the template in the editor.
require 'rubygems'
require "watir"
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
 
  def self.create()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/login.xhtml") 
    bouton_2 = $browser.button(:id, "j_idt9:formLogin:btnCreateCount")
    bouton_2.click
    sleep 2
  end
  
  def self.deconnect()
    decon = $browser.img(:id,"logout")
    decon.click
    sleep 3
  end
end
class Test_login < Test::Unit::TestCase
  def testSwitchToViewAgenda
    Login.init()
    Login.connect()
    title = $browser.url
    assert_equal("http://localhost:8080/PILAgenda-war/faces/viewAgenda.xhtml",title)
  end  
end

class Test_login_2 < Test::Unit::TestCase
  def testSwitchToCrateCount
    Login.create()
    title_1 = $browser.url
    assert_equal("http://localhost:8080/PILAgenda-war/faces/createAccount.xhtml",title_1)
  end
end
class Test_logout <Test::Unit::TestCase
  def testLogout
    Login.connect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/viewAgenda.xhtml")
    Login.deconnect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/viewAgenda.xhtml")
    title = $browser.url
    assert_equal($browser.goto("http://localhost:8080/PILAgenda-war/faces/login.xhtml"),title)
    Login.connect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/createAgenda.xhtml")
    Login.deconnect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/createAgenda.xhtml")
    title = $browser.url
    assert_equal($browser.goto("http://localhost:8080/PILAgenda-war/faces/login.xhtml"),title)
    Login.connect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/createTask.xhtml")
    Login.deconnect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/createTask.xhtml")
    title = $browser.url
    assert_equal($browser.goto("http://localhost:8080/PILAgenda-war/faces/login.xhtml"),title)
    Login.connect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/createEvent.xhtml") 
    Login.deconnect()
    $browser.goto("http://localhost:8080/PILAgenda-war/faces/createEvent.xhtml")
    title = $browser.url
    assert_equal($browser.goto("http://localhost:8080/PILAgenda-war/faces/login.xhtml"),title)
    $browser.close
    #$browser.text.include?("avance")
  end
end