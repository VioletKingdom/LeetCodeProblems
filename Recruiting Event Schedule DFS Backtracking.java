public List<String> allSchedules(List<String> universities){
	List<String> result = new ArrayList<>();
	if (universities == null || universitites.size() == 0){
		return result;
	}
	StringBuilder sb = new StringBuilder();
	sb.append(universities.get(0));
	int index = 1;
	helper(universities, index, sb, result);
	return result;
}

public void helper(List<String> universities, int index, StringBuilder sb, List<String> result){
	if (index == universities.size()){
		result.add(new String(sb));
		return;
	}
	sb.append(universities.get(index));
	helper(universities, index+1, sb, result);
	sb.remove(sb.length()-1);

	sb.append('x');
	sb.append(universities.get(index));
	helper(universities, index+1, sb, result);
	sb.remove(sb.length()-1);
}

