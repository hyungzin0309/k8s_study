cluster
- Role : EKS
- 이름 : k8s_study

node 생성
- Role : eksnode
- 이름 : k8s_study
- 용량 : disk 최소 10
- 사양 : t3.medium
- 개수 : 1개씩만
    
    
새 노드 연결
1. ~/.kube/config 에서 기존 cluster, context, user 정보 지우고, current-context 를 docker-desktop 으로 지정
2. aws eks update-kubeconfig --region us-east-1 --name k8s_study
3. k config use-context arn:aws:eks:us-east-1:590183891444:cluster/k8s_study
4. EKS Cluster 의 엑세스 탭에서 study 사용자 추가
 