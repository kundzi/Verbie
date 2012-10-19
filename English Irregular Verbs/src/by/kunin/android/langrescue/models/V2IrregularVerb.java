package by.kunin.android.langrescue.models;

public class V2IrregularVerb {
	
	private final String mInfinitive;
	private final String mSimple;
	private final String mParticiple;
	
	private final int mId;
	
	public V2IrregularVerb(int id, String infinitive, String simple, String participle) {
		mId = id;
		mInfinitive = infinitive;
		mSimple = simple;
		mParticiple = participle;
	}
	
	public V2IrregularVerb(int id, String verbforms) {
		this(id, verbforms.split("\\s")[0], verbforms.split("\\s")[1], verbforms.split("\\s")[2]);
	}

	public String getInfinitive() {
		return mInfinitive;
	}

	public String getSimple() {
		return mSimple;
	}

	public String getParticiple() {
		return mParticiple;
	}

	public int getId() {
		return mId;
	}
	
	@Override
	public String toString() {
		String result = String.format("%s %s %s", mInfinitive, mSimple, mParticiple);
		return result;
	}

}
