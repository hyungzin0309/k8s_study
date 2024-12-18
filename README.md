
<div>
<img src="./images/k8s.png" style="width:700px"></img>
</div>

## Kubernetes | K8S

쿠버네티스(Kubernetes)는 컨테이너화된 애플리케이션의 배포, 확장, 관리를 자동화하기 위한 플랫폼이다. 쉽게 말해, 여러 개의 컨테이너를 하나의 클러스터로 묶어서 효율적으로 운영할 수 있도록 도와주는 컨테이너 오케스트레이션 도구이다.

컨테이너는 애플리케이션을 격리된 환경에서 실행할 수 있도록 도와주는 기술이지만, 수십 개에서 수백 개 이상의 컨테이너를 수동으로 관리하는 것은 매우 어렵다. 쿠버네티스는 이 과정을 자동화하여 개발자와 운영자의 부담을 줄이고, 애플리케이션이 항상 안정적으로 동작하도록 보장한다.

## 탄생배경


2000년대 초반, Google은 웹 애플리케이션을 대규모로 운영하면서 수많은 서버를 관리해야 했다. 이 과정에서 워크로드를 효과적으로 분산하고 운영 비용을 줄이기 위해 컨테이너 기술을 도입했다. 하지만 컨테이너가 늘어나면서 다음과 같은 관리 문제가 발생했다.

- 어떤 서버에서 어떤 컨테이너를 실행할지 결정해야 하는 문제
- 컨테이너 실행 중 장애가 발생했을 때 이를 복구하는 문제
- 서버 자원을 효율적으로 사용하기 위한 배치 문제

이 문제를 해결하기 위해 Google은 내부적으로 Borg라는 컨테이너 관리 시스템을 개발했고, 이후 이 기술을 확장하여 Kubernetes라는 오픈소스 프로젝트로 공개했다.


## 활용

K8S 활용 사례
- https://kubernetes.io/case-studies/

용도
- MSA 적용
- 개발, 연구용 (내부)

추세
- 기존에는 Netflix OSS 많이 사용
- 현재는 Netflix oss 관련 컴포넌트들이 버전관리가 잘 안되고 있는 문제?

## 배포 구조

![deploy_arch.png](images%2Fdeploy_arch.png)
- namespace
  - app 및 service 를 분리하여 관리하기 위한 테넌트
- deploy
  - 하나의 service (논리적 단위)
- pod
  - 실제 리소스를 사용하는 단위. 하나, 혹은 다수의 container 로 구성됨
- container
  - pod 당 container 는 보통 하나
  - 여러개를 사용하는 경우
    - sidecar 패턴으로 활용하여 중심 container 를 모니터링하거나 로깅
      - 애플리케이션 로그를 중앙 시스템(예: Elasticsearch, Fluentd)으로 전송
    - 어댑터 패턴
      - 컨테이너에 들어오는 데이터, 밖으로 쏘는 데이터 변환
- service
  - 하나의 deploy 를 대상으로 로드밸런싱 수행
- ingress
  - 복잡한 라우팅 규칙 처리
- volume
  - pod 에 직접 마운팅
    - nfs, host path, pvc, ...
  - pv / pvc
    - host, nfs 등 다양한 대상 스토리지에 볼륨 마운트 가능
    - 대상 스토리지는 pv 에서 정의
    - deploy 대상 스토리지를 추상화한 pvc 에 볼륨 마운트
    
## 어플리케이션 배포
- 배포된 클러스터, 어플리케이션 확인
- 어플리케이션 수정
- 배포스크립트, yaml 확인

## 클러스터 구조

![cluster_arch.png](images%2Fcluster_arch.png)
https://kubernetes.io/ko/docs/concepts/overview/components/
1. API Server (kube-apiserver)
    - Kubernetes의 중심 컴포넌트로, 모든 클라이언트 요청의 진입점 역할을 함

2. etcd
    - 클러스터 상태 데이터를 저장하는 분산 키-값 저장소
    - 예 : deploy 생성 명령이 들어오면 해당 데이터를 etcd 에 먼저 저장 후 워커노드에 전달. 후에 컨트롤러가 etcd 와 노드를 확인하며, 상태가 불일치하는 대상에 대해 일치시키려고 함

3. Controller Manager (kube-controller-manager)
    - 클러스터 상태를 관찰하고, 선언된 상태에 맞게 조정
    - 여러 컨트롤러의 실행을 담당하는 프로세스
        - 	Node Controller: 노드 상태 확인 및 장애 감지
        - 	Replication Controller: 파드 복제본 유지
        - 	Endpoint Controller: 서비스와 파드 연결 관리
        - 	Service Account & Token Controller: API 접근 권한 관리

4. Scheduler (kube-scheduler)
    - 새로 생성된 파드를 적절한 워커 노드에 배치
    - 논리적으로 하나의 deploy 로 묶인 파드들도

## 써드파티

- Helm
  - 패키지 매니저

- Prometheus + Grafana
  - 수집 및 모니터링 툴

- ArgoCD
    - gitops 기반 컨테이너 자동 배포
    - Jenkins 조합

- LENS
  - Kubernetes 클러스터를 관리하고 모니터링할 수 있는 데스크톱 GUI 클라이언트
  - 여러 클러스터를 손쉽게 관리할 수 있도록 직관적인 UI와 다양한 기능을 제공
  - https://k8slens.dev/

## 스터디 준비하면서 이슈

- 도커 이미지 빌드 시 엔진의 CPU 아키텍처 적용

