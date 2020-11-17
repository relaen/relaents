import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Tunnel} from './tunnel'
import {Device} from './device'

@Entity("t_device_loc",'tunnel')
export class DeviceLoc extends BaseEntity{
	@Id()
	@Column({
		name:'device_loc_id',
		type:'int',
		nullable:false
	})
	private deviceLocId:number;

	@ManyToOne({entity:'Tunnel',eager:false})
	@JoinColumn({name:'tunnel_id',refName:'tunnel_id'})
	private tunnel:Tunnel;

	@Column({
		name:'loc_no',
		type:'int',
		nullable:true
	})
	private locNo:number;

	@Column({
		name:'x',
		type:'double',
		nullable:true
	})
	private x:number;

	@Column({
		name:'y',
		type:'double',
		nullable:true
	})
	private y:number;

	@Column({
		name:'z',
		type:'double',
		nullable:true
	})
	private z:number;

	@Column({
		name:'loc_desc',
		type:'string',
		nullable:true
	})
	private locDesc:string;

	@OneToMany({entity:'Device',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceLoc',eager:false})
	private devices:Array<Device>;

	public getDeviceLocId():number{
		return this.deviceLocId;
	}
	public setDeviceLocId(value:number){
		this.deviceLocId = value;
	}

	public getTunnel():Tunnel{
		return this.tunnel;
	}
	public setTunnel(value:Tunnel){
		this.tunnel = value;
	}

	public getLocNo():number{
		return this.locNo;
	}
	public setLocNo(value:number){
		this.locNo = value;
	}

	public getX():number{
		return this.x;
	}
	public setX(value:number){
		this.x = value;
	}

	public getY():number{
		return this.y;
	}
	public setY(value:number){
		this.y = value;
	}

	public getZ():number{
		return this.z;
	}
	public setZ(value:number){
		this.z = value;
	}

	public getLocDesc():string{
		return this.locDesc;
	}
	public setLocDesc(value:string){
		this.locDesc = value;
	}

	public getDevices():Array<Device>{
		return this.devices;
	}
	public setDevices(value:Array<Device>){
		this.devices = value;
	}

}