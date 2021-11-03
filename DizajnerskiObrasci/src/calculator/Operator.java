package calculator;

import java.text.DecimalFormat;

public class Operator {
	DecimalFormat dfInterface;
	private double currentValue;
	private OperatorState state;
	private StringBuilder operationLog;

	public Operator() {
		state = OperatorState.UNSET;
		operationLog = new StringBuilder();
		dfInterface = new DecimalFormat();
		dfInterface.setDecimalSeparatorAlwaysShown(false);
		dfInterface.setGroupingUsed(false);
	}

	private void CalculatePrevious(double number) {
		if (state == OperatorState.UNSET) {
			currentValue = number;
			operationLog.setLength(0);
			operationLog.append(dfInterface.format(number));

		} else {
			switch (state) {
			case ADDITION:
				currentValue += number;
				operationLog.append("+" + dfInterface.format(number));
				break;
			case SUBTRACTION:
				currentValue -= number;
				operationLog.append("-" + dfInterface.format(number));
				break;
			case MULTIPLICATION:
				currentValue *= number;
				operationLog.append("*" + dfInterface.format(number));
				break;
			case DEVISION:
				currentValue /= number;
				operationLog.append("/" + dfInterface.format(number));
				break;
			default:
				break;
			}
		}
	}

	public void Add(double number) {
		CalculatePrevious(number);
		if (state == OperatorState.UNSET) {
			operationLog.append("+");
		}
		state = OperatorState.ADDITION;
	}

	public void Clear() {
		currentValue = 0;
		state = OperatorState.UNSET;
		operationLog.setLength(0);
	}

	public void Conclude(double number) {
		CalculatePrevious(number);
		state = OperatorState.UNSET;
		operationLog.setLength(0);

	}

	public void Devide(double number) {
		CalculatePrevious(number);
		if (state == OperatorState.UNSET) {
			operationLog.append("/");
		}
		state = OperatorState.DEVISION;
	}

	public void Multiply(double number) {
		CalculatePrevious(number);
		if (state == OperatorState.UNSET) {
			operationLog.append("*");
		}
		state = OperatorState.MULTIPLICATION;
	}

	public void Subtract(double number) {
		CalculatePrevious(number);
		if (state == OperatorState.UNSET) {
			operationLog.append("-");
		}
		state = OperatorState.SUBTRACTION;
	}

	public OperatorState getState() {
		return state;
	}

	public double getValue() {
		return currentValue;
	}

	public String getValueFormatted() {
		return dfInterface.format(this.getValue());
	}

	public String getOperationLog() {
		return operationLog.toString();
	}
}
