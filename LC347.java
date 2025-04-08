import java.util.*;

// TC: O(n log k)
// SC: O(n + k)
// Approach 1
public class LC347 {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a,b)->(a[1]-b[1]));
        for(int i =0;i<n;i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        for(int key : map.keySet()){
            pq.add(new Integer[]{key, map.get(key)});
            if(pq.size()>k) pq.poll();
        }
        int ans[] = new int[k];
        for(int i=0;i<k;i++){
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}

// TC: O(n+k)
// SC: O(n)
// Approach 2
class LC347_2 {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            max = Math.max(max, map.get(nums[i]));
        }
        List<Integer>[] list = new ArrayList[max + 1];
        for (int k1 : map.keySet()) {
            int f = map.get(k1);
            if (list[f] == null) {
                list[f] = new ArrayList<>();
            }
            list[f].add(k1);
        }
        int ans[] = new int[k];
        int ptr = 0;
        for (int i = max; i > 0 && ptr < k; i--) {
            if (list[i] == null)
                continue;
            for (int j = 0; j < list[i].size() && ptr < k; j++) {
                ans[ptr++] = list[i].get(j);
            }
        }
        return ans;
    }
}