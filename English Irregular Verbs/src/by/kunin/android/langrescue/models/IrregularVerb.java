package by.kunin.android.langrescue.models;

import static com.google.common.base.Objects.equal;

import java.io.Serializable;
import java.util.Scanner;

public class IrregularVerb implements Serializable, Comparable<IrregularVerb> {
    private static final long serialVersionUID = 5095729725398805499L;
    
    private String infinitive = "";
	private String pastSimple = "";
	private String pastParticiple = "";
	private int id;

	public IrregularVerb(String input) {
		setVerbs(input);
	}

	public IrregularVerb(String inf, String past, String part) {
		super();
		this.infinitive = inf;
		this.pastSimple = past;
		this.pastParticiple = part;
	}

	private void setVerbs(String input) {
		Scanner scanner = new Scanner(input);
		infinitive = scanner.next();
		pastSimple = scanner.next();
		pastParticiple = scanner.next();
	}

	public String getInfinitive() {
		return infinitive;
	}

	public String getPastSimple() {
		return pastSimple;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}

	public int getId() {
		return id;
	}
	
	public boolean same(IrregularVerb another) {
	    return equal(this.infinitive, another.infinitive);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (this == obj) return true;
	    if (this.getClass() != obj.getClass()) return false;
	    
	    IrregularVerb another = (IrregularVerb)obj;
	    return equal(this.infinitive, another.infinitive) && 
	            equal(this.pastSimple, another.pastSimple) &&
	            equal(this.pastParticiple, another.pastParticiple);
	}
	
	@Override
	public int hashCode() {
	    return infinitive.hashCode() + pastSimple.hashCode() + pastParticiple.hashCode();
	}


	@Override
	public String toString() {
		String result = String.format("%s %s %s", infinitive, pastSimple, pastParticiple);
		return result;
	}

    @Override
    public int compareTo(IrregularVerb o) {
        return this.infinitive.compareTo(o.infinitive);
    }
}
