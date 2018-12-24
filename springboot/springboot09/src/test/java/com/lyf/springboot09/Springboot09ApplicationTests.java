package com.lyf.springboot09;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot09ApplicationTests {

    /**
     * 会默认按照Resources目录下的activiti.cfg.xml创建流程引擎
     */
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    @Test
    public void contextLoads() {

        //以下两种方式选择一种创建引擎方式：1.配置写在程序里 2.读对应的配置文件
        //1
        testCreateProcessEngine();
        //2
        testCreateProcessEngineByCfgXml();

        deployProcess();
        startProcess();
        queryTask();
        //handleTask();
    }
    /**
     * 测试activiti环境
     */
    @Test
    public void testCreateProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        cfg.setJdbcDriver("com.mysql.jdbc.Driver");
        cfg.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activiti");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("1234");
        //配置建表策略
        cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine engine = cfg.buildProcessEngine();
    }

    /**
     * 根据配置文件activiti.cfg.xml创建ProcessEngine
     * 创建Activiti流的相关的数据库表
     */
    @Test
    public void testCreateProcessEngineByCfgXml() {
        ProcessEngine processEngine  = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();;
    }

    /**
     * 1发布流程
     * RepositoryService
     * 运行成功后，查看之前的数据库表，就会发现多了很多内容
     */
    @Test
    public void deployProcess() {
        //  //加载的那两个内容就是我们之前已经弄好的基础内容哦。
        //得到了流程引擎
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        // 加载流程定义文件
        builder.addClasspathResource("b.bpmn");
        builder.addClasspathResource("b.png");
        builder.deploy();
    }

    /**
     *  2：启动流程实例
     * RuntimeService
     */
    @Test
    public void startProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //可根据id、key、message启动流程
        runtimeService.startProcessInstanceByKey("审批流程"); //这个是查看数据库中act_re_procdef表的 key
    }


    /**
     * 3办理任务
     */
    @Test
    public void handleTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据上一步生成的taskId执行任务  业务员请假
        String taskId = "2504";

        //经理审批
        //String taskId = "2504";
        taskService.complete(taskId);//查看act_ru_task表
    }

    /**
     * 查看任务
     * TaskService
     */
    @Test
    public void queryTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据assignee(代理人)查询任务
        String emp = "emp";
        String manager = "manager";
        List<Task> emps = taskService.createTaskQuery().taskAssignee(emp).list();
        List<Task> managers = taskService.createTaskQuery().taskAssignee(manager).list();
//        int size = emps.size();
//        for (int i = 0; i < size; i++) {
//            Task task = emps.get(i);
//
//        }

        for (Task task : managers) {
            System.out.println("manager_taskId:" + task.getId() +
                    ",manager_taskName:" + task.getName() +
                    ",manager_assignee:" + task.getAssignee() +
                    ",manager_createTime:" + task.getCreateTime());
        }
        for (Task task : emps) {
            System.out.println("emp_taskId:" + task.getId() +
                    ",emp_taskName:" + task.getName() +
                    ",emp_assignee:" + task.getAssignee() +
                    ",emp_createTime:" + task.getCreateTime());
        }
    }

}

