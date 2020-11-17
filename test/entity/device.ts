import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {DeviceModel} from './devicemodel'
import {DeviceLoc} from './deviceloc'
import {IotServer} from './iotserver'
import {ChannelCfg} from './channelcfg'
import {DeviceLog} from './devicelog'

@Entity("t_device",'tunnel')
export class Device extends BaseEntity{
	@Id()
	@Column({
		name:'devide_id',
		type:'int',
		nullable:false
	})
	private devideId:number;

	@ManyToOne({entity:'DeviceModel',eager:false})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	private deviceModel:DeviceModel;

	@ManyToOne({entity:'DeviceLoc',eager:false})
	@JoinColumn({name:'device_loc_id',refName:'device_loc_id'})
	private deviceLoc:DeviceLoc;

	@ManyToOne({entity:'IotServer',eager:false})
	@JoinColumn({name:'iot_server_id',refName:'iot_server_id'})
	private iotServer:IotServer;

	@Column({
		name:'device_no',
		type:'string',
		nullable:true
	})
	private deviceNo:string;

	@Column({
		name:'device_code',
		type:'string',
		nullable:true
	})
	private deviceCode:string;

	@Column({
		name:'children_num',
		type:'int',
		nullable:true
	})
	private childrenNum:number;

	@Column({
		name:'start_time',
		type:'int',
		nullable:true
	})
	private startTime:number;

	@Column({
		name:'end_time',
		type:'int',
		nullable:true
	})
	private endTime:number;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	private enabled:number;

	@Column({
		name:'stop_reason',
		type:'string',
		nullable:true
	})
	private stopReason:string;

	@Column({
		name:'lane_no',
		type:'int',
		nullable:true
	})
	private laneNo:number;

	@Column({
		name:'collect_freq',
		type:'int',
		nullable:true
	})
	private collectFreq:number;

	@OneToMany({entity:'ChannelCfg',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceForChildDevideId',eager:false})
	private channelCfgForChildDevideIds:Array<ChannelCfg>;

	@OneToMany({entity:'ChannelCfg',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceForParentDevideId',eager:false})
	private channelCfgForParentDevideIds:Array<ChannelCfg>;

	@OneToMany({entity:'DeviceLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'device',eager:false})
	private deviceLogs:Array<DeviceLog>;

	public getDevideId():number{
		return this.devideId;
	}
	public setDevideId(value:number){
		this.devideId = value;
	}

	public getDeviceModel():DeviceModel{
		return this.deviceModel;
	}
	public setDeviceModel(value:DeviceModel){
		this.deviceModel = value;
	}

	public getDeviceLoc():DeviceLoc{
		return this.deviceLoc;
	}
	public setDeviceLoc(value:DeviceLoc){
		this.deviceLoc = value;
	}

	public getIotServer():IotServer{
		return this.iotServer;
	}
	public setIotServer(value:IotServer){
		this.iotServer = value;
	}

	public getDeviceNo():string{
		return this.deviceNo;
	}
	public setDeviceNo(value:string){
		this.deviceNo = value;
	}

	public getDeviceCode():string{
		return this.deviceCode;
	}
	public setDeviceCode(value:string){
		this.deviceCode = value;
	}

	public getChildrenNum():number{
		return this.childrenNum;
	}
	public setChildrenNum(value:number){
		this.childrenNum = value;
	}

	public getStartTime():number{
		return this.startTime;
	}
	public setStartTime(value:number){
		this.startTime = value;
	}

	public getEndTime():number{
		return this.endTime;
	}
	public setEndTime(value:number){
		this.endTime = value;
	}

	public getEnabled():number{
		return this.enabled;
	}
	public setEnabled(value:number){
		this.enabled = value;
	}

	public getStopReason():string{
		return this.stopReason;
	}
	public setStopReason(value:string){
		this.stopReason = value;
	}

	public getLaneNo():number{
		return this.laneNo;
	}
	public setLaneNo(value:number){
		this.laneNo = value;
	}

	public getCollectFreq():number{
		return this.collectFreq;
	}
	public setCollectFreq(value:number){
		this.collectFreq = value;
	}

	public getChannelCfgForChildDevideIds():Array<ChannelCfg>{
		return this.channelCfgForChildDevideIds;
	}
	public setChannelCfgForChildDevideIds(value:Array<ChannelCfg>){
		this.channelCfgForChildDevideIds = value;
	}

	public getChannelCfgForParentDevideIds():Array<ChannelCfg>{
		return this.channelCfgForParentDevideIds;
	}
	public setChannelCfgForParentDevideIds(value:Array<ChannelCfg>){
		this.channelCfgForParentDevideIds = value;
	}

	public getDeviceLogs():Array<DeviceLog>{
		return this.deviceLogs;
	}
	public setDeviceLogs(value:Array<DeviceLog>){
		this.deviceLogs = value;
	}

}