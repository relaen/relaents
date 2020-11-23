import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Road} from './road'
import {TunnelType} from './tunneltype'
import {Agent} from './agent'
import {DeviceLoc} from './deviceloc'
import {IotServer} from './iotserver'

@Entity("t_tunnel",'tunnel')
export class Tunnel extends BaseEntity{
	@Id()
	@Column({
		name:'tunnel_id',
		type:'int',
		nullable:false
	})
	private tunnelId:number;

	@ManyToOne({entity:'Road',eager:false})
	@JoinColumn({name:'road_id',refName:'road_id'})
	private road:Road;

	@ManyToOne({entity:'TunnelType',eager:false})
	@JoinColumn({name:'tunnel_type_id',refName:'tunnel_type_id'})
	private tunnelType:TunnelType;

	@ManyToOne({entity:'Agent',eager:false})
	@JoinColumn({name:'agent_id',refName:'agent_id'})
	private agent:Agent;

	@Column({
		name:'turnal_no',
		type:'string',
		nullable:true
	})
	private turnalNo:string;

	@Column({
		name:'longitude',
		type:'double',
		nullable:true
	})
	private longitude:number;

	@Column({
		name:'latitude',
		type:'double',
		nullable:true
	})
	private latitude:number;

	@Column({
		name:'length',
		type:'double',
		nullable:true
	})
	private length:number;

	@Column({
		name:'pure_width',
		type:'double',
		nullable:true
	})
	private pureWidth:number;

	@Column({
		name:'road_width',
		type:'double',
		nullable:true
	})
	private roadWidth:number;

	@Column({
		name:'pure_height',
		type:'double',
		nullable:true
	})
	private pureHeight:number;

	@Column({
		name:'start_time',
		type:'int',
		nullable:true
	})
	private startTime:number;

	@Column({
		name:'lane_num',
		type:'int',
		nullable:true
	})
	private laneNum:number;

	@Column({
		name:'loc_desc',
		type:'string',
		nullable:true
	})
	private locDesc:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	private remarks:string;

	@OneToMany({entity:'DeviceLoc',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'tunnel',eager:false})
	private deviceLocs:Array<DeviceLoc>;

	@OneToMany({entity:'IotServer',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'tunnel',eager:false})
	private iotServers:Array<IotServer>;

	public getTunnelId():number{
		return this.tunnelId;
	}
	public setTunnelId(value:number){
		this.tunnelId = value;
	}

	public getRoad():Road{
		return this.road;
	}
	public setRoad(value:Road){
		this.road = value;
	}

	public getTunnelType():TunnelType{
		return this.tunnelType;
	}
	public setTunnelType(value:TunnelType){
		this.tunnelType = value;
	}

	public getAgent():Agent{
		return this.agent;
	}
	public setAgent(value:Agent){
		this.agent = value;
	}

	public getTurnalNo():string{
		return this.turnalNo;
	}
	public setTurnalNo(value:string){
		this.turnalNo = value;
	}

	public getLongitude():number{
		return this.longitude;
	}
	public setLongitude(value:number){
		this.longitude = value;
	}

	public getLatitude():number{
		return this.latitude;
	}
	public setLatitude(value:number){
		this.latitude = value;
	}

	public getLength():number{
		return this.length;
	}
	public setLength(value:number){
		this.length = value;
	}

	public getPureWidth():number{
		return this.pureWidth;
	}
	public setPureWidth(value:number){
		this.pureWidth = value;
	}

	public getRoadWidth():number{
		return this.roadWidth;
	}
	public setRoadWidth(value:number){
		this.roadWidth = value;
	}

	public getPureHeight():number{
		return this.pureHeight;
	}
	public setPureHeight(value:number){
		this.pureHeight = value;
	}

	public getStartTime():number{
		return this.startTime;
	}
	public setStartTime(value:number){
		this.startTime = value;
	}

	public getLaneNum():number{
		return this.laneNum;
	}
	public setLaneNum(value:number){
		this.laneNum = value;
	}

	public getLocDesc():string{
		return this.locDesc;
	}
	public setLocDesc(value:string){
		this.locDesc = value;
	}

	public getRemarks():string{
		return this.remarks;
	}
	public setRemarks(value:string){
		this.remarks = value;
	}

	public getDeviceLocs():Array<DeviceLoc>{
		return this.deviceLocs;
	}
	public setDeviceLocs(value:Array<DeviceLoc>){
		this.deviceLocs = value;
	}

	public getIotServers():Array<IotServer>{
		return this.iotServers;
	}
	public setIotServers(value:Array<IotServer>){
		this.iotServers = value;
	}

}