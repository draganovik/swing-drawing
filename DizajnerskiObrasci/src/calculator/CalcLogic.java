package calculator;

import java.text.DecimalFormat;

public class CalcLogic {
	private Operation activeOperation;
	DecimalFormat dfInterface;
	private StringBuilder interfaceText;
	private StringBuilder operationLog;
	private double operationResult;

	public CalcLogic() {
		this.setUp();
		dfInterface = new DecimalFormat();
		dfInterface.setDecimalSeparatorAlwaysShown(false);
		dfInterface.setGroupingUsed(false);
	}

	private void setUp() {
		this.operationResult = 0;
		this.interfaceText = new StringBuilder("0");
		this.operationLog = new StringBuilder();
	}

	private double formatedNumber() {
		return Double.parseDouble(this.interfaceText.toString());
	}

	private void calculate(Operation operation) {

		if (this.activeOperation != null) {
			switch (this.activeOperation) {
			case ADDITION:
				this.operationResult += formatedNumber();
				break;
			case SUBTRACTION:
				this.operationResult -= formatedNumber();
				break;
			case MULTIPLICATION:
				this.operationResult *= formatedNumber();
				break;
			case DEVISION:
				this.operationResult /= formatedNumber();
				break;
			}
			this.operationLog.append(formatedNumber());
		} else {
			if (this.interfaceText.length() != 0) {
				this.operationResult = formatedNumber();
			}
			this.operationLog.append(this.getOperationResult());

		}
		if (operation != null) {
			switch (operation) {
			case ADDITION:
				this.operationLog.append(" + ");
				break;
			case SUBTRACTION:
				this.operationLog.append(" - ");
				break;
			case MULTIPLICATION:
				this.operationLog.append(" * ");
				break;
			case DEVISION:
				this.operationLog.append(" / ");
				break;
			default:
				this.operationLog.append(" UD ");
				break;
			}
			this.activeOperation = operation;
			this.interfaceText.setLength(0);
		}
	}

	public void TypeIn(String input) {
		if (input == ".") {
			if (this.interfaceText.indexOf(".") == -1) {
				if (this.interfaceText.length() == 0) {
					this.interfaceText.append("0");
				}
				this.interfaceText.append(input);
				return;
			}
			return;
		}
		if (input == "0") {
			if ((this.interfaceText.indexOf(".") != -1) || this.getCurrentInput() != "0") {
				this.interfaceText.append(input);
				return;
			}
			return;
		}
		if (interfaceText.toString().equals("0")) {
			interfaceText.setLength(0);
		}
		this.interfaceText.append(input);
	}

	/*
	 * Calculator Options
	 */

	public void Equalize() {
		if (this.interfaceText.length() > 0) {
			this.calculate(activeOperation);
		}
		this.interfaceText.setLength(0);

		this.operationLog.setLength(0);
		activeOperation = null;

	}

	public void Clear() {
		this.setUp();
	}

	public void Delete() {
		this.interfaceText.setLength(interfaceText.length() - 1);
		if (this.interfaceText.length() == 0) {
			this.interfaceText.append("0");
		}
	}

	public void Add() {
		this.calculate(Operation.ADDITION);
	}

	public void Subtract() {
		this.calculate(Operation.SUBTRACTION);
	}

	public void Multiply() {
		this.calculate(Operation.MULTIPLICATION);
	}

	public void Devide() {
		this.calculate(Operation.DEVISION);
	}

	/*
	 * Getters
	 */

	public String getCurrentInput() {
		return interfaceText.toString();
	}

	public String getOperationLog() {
		return this.operationLog.toString();
	}

	public String getOperationResult() {
		return dfInterface.format(this.operationResult);
	}

}
