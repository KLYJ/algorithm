import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
				// 장르별 재생 횟수 저장
				HashMap<String, Integer> map = new HashMap<>();
				for (int i = 0; i < genres.length; i++) {
						String gr = genres[i];
						if (map.containsKey(gr))
								map.put(gr, map.get(gr) + plays[i]);
						else
								map.put(gr, plays[i]);
				}

				// 재생 횟수 많은 순서대로 장르 내림차순
				List<String> genre = new ArrayList<>(map.keySet());
				genre.sort((o1, o2) -> (map.get(o2) - map.get(o1)));

				// 재생 횟수 많은 장르 순으로 우선순위(0, 1, 2 ...) 부여
				int play[][] = new int[plays.length][3]; // [우선순위, plays, 고유번호] 저장
				for (int i = 0; i < plays.length; i++) {
						play[i][0] = genre.indexOf(genres[i]);
						play[i][1] = plays[i];
						play[i][2] = i;
				}

				// 장르 총 재생 횟수, 같은 장르 내 재생횟수, 같은 재생횟수 일 때 고유번호 기준으로 정렬
				Arrays.sort(play, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
								int genre_pri = o1[0] - o2[0]; // 장르의 총 재생횟수 기준 오름차순
								if (genre_pri == 0) {
											int plays_pri = o2[1] - o1[1]; // 재생 횟수 기준 내림차순
											if (plays_pri == 0) {
													return o1[2] - o2[2]; // 고유번호 기준 오름차순
											} else
													return plays_pri;
								}
								return genre_pri;
						}
				});
	
		 		//장르 별로 2개나 1개씩 선택하고 고유번호를 answer에 저장
				List<Integer> answer = new ArrayList<>();
				int g = play[0][0];
				int g_cnt = 1;
				answer.add(play[0][2]);
				for (int i = 1; i < play.length; i++) {
							if (g == play[i][0]) { // 장르 같다면
									if (g_cnt < 2) {   // 아직 같은 장르 내에서 2개를 선택하기 전
											answer.add(play[i][2]);
											g_cnt++;
									}
									else
											continue;
							}
							else {  //장르 다름
									g = play[i][0];
									g_cnt = 1;
									answer.add(play[i][2]);
							}
				}
		
				//List -> 배열
				int[] result = new int[answer.size()];
				for(int i=0;i<answer.size();i++) {
						result[i] = answer.get(i);
				}
		
				return result;
    }
}
