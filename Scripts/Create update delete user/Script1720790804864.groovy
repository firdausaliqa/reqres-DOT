import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'create new user and get id'
response1 = WS.sendRequest(findTestObject('Users/Create new user'))

def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(response1.getResponseBodyContent())

//create variable to save lastname
def idx = result.id
def name = result.name
println('id number is ' + idx)
println('name is ' + name)

//passing value x1 as Global Variable
GlobalVariable.id = idx
GlobalVariable.name = name
println('Global variable id is ' + GlobalVariable.id)
println('Global variable name is ' + GlobalVariable.name)

'update new user based on global variable id'
WS.sendRequestAndVerify(findTestObject('Users/Update existing user'))

'delete existing user based on global variable id for chaining'
WS.sendRequest(findTestObject('Users/Delete existing user'))

